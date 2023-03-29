package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;

public interface ProductService {
    Product save(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
}
