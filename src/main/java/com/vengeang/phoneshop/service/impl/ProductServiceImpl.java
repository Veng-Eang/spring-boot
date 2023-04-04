package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.dto.PriceDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.entities.ProductImportHistory;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.mapper.ProductMapper;
import com.vengeang.phoneshop.repositories.ProductImportHistoryRepository;
import com.vengeang.phoneshop.repositories.ProductRepository;
import com.vengeang.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        String name="%s %s".formatted(product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Product with id : %d not found ".formatted(id)));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
//       update available  product unit
        Product product = getById(importDTO.getProductId());
        Integer availableUnit=product.getAvailableUnit()!=null? product.getAvailableUnit()+ importDTO.getImportUnit() : importDTO.getImportUnit();
        product.setAvailableUnit(availableUnit);
        productRepository.save(product);
//        save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(productImportHistory);
    }

    @Override
    public void setSalePrice(Long productId, PriceDTO priceDTO) {
        Product product = getById(productId);
        BigDecimal price = priceDTO.getPrice();
        product.setSalePrice(price);
        productRepository.save(product);
    }
}
