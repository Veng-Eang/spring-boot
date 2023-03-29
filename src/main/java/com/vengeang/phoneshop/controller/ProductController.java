package com.vengeang.phoneshop.controller;

import com.vengeang.phoneshop.dto.ProductDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.mapper.ProductMapper;
import com.vengeang.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = productService.save(productMapper.toProduct(productDTO));
        return ResponseEntity.ok(product);
    }
    @PostMapping("import")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO importDTO){
        productService.importProduct(importDTO);
        return ResponseEntity.ok("Import success");
    }

}
