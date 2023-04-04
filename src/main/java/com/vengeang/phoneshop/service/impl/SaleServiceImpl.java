package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.dto.ProductSoldDTO;
import com.vengeang.phoneshop.dto.SaleDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.entities.Sale;
import com.vengeang.phoneshop.entities.SaleDetail;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.mapper.SaleMapper;
import com.vengeang.phoneshop.repositories.ProductRepository;
import com.vengeang.phoneshop.repositories.SaleDetailRepository;
import com.vengeang.phoneshop.repositories.SaleRepository;
import com.vengeang.phoneshop.service.ProductService;
import com.vengeang.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final SaleMapper saleMapper;
//    private final
    @Override
    public void sell(SaleDTO saleDTO) {
    List<Long> productIds = saleDTO.getProducts().stream()
            .map(ProductSoldDTO::getProductId)
            .toList();
//      validation
    saleDTO.getProducts().stream()
            .map(ProductSoldDTO::getProductId)
            .forEach(productService::getById);
//      validate stock
    List<Product> products = productRepository.findAllById(productIds);
    Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    saleDTO.getProducts().forEach(
            productSoldDTO -> {
                Product product = productMap.get(productSoldDTO.getProductId());
                try {
                    if(product.getAvailableUnit()<productSoldDTO.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST,"Product[%s] not enough in stock".formatted(product.getName()));
                    }
                }catch (NullPointerException e){
                    throw new ApiException(HttpStatus.BAD_REQUEST,"Number of unit can't be null!!");
                }

            }
    );
//        save
//        sale
        Sale sale = saleMapper.toSale(saleDTO);
        saleRepository.save(sale);
//        sale detail
        saleDTO.getProducts().forEach(
                productSoldDTO -> {
                    Product product = productMap.get(productSoldDTO.getProductId());
                    SaleDetail saleDetail=new SaleDetail();
                    saleDetail.setSale(sale);
                    saleDetail.setProduct(product);
                    saleDetail.setAmount(product.getSalePrice());
                    saleDetail.setUnit(productSoldDTO.getNumberOfUnit());
                    saleDetail.setSoldDate(saleDTO.getSoldDate());
                    saleDetailRepository.save(saleDetail);
                    Integer availableUnit=product.getAvailableUnit()-productSoldDTO.getNumberOfUnit();
                    product.setAvailableUnit(availableUnit);
                    productRepository.save(product);
                }
        );

    }
}
