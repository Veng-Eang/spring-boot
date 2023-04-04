package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.dto.PriceDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product save(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, PriceDTO price);
}
