package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.dto.PriceDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product save(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, PriceDTO price);
    Map<Integer,String> uploadProduct(MultipartFile file);
    Product getByModelIdAndColorId(Long modelId,Long colorId);
}
