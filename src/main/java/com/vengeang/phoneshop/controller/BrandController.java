package com.vengeang.phoneshop.controller;


import com.vengeang.phoneshop.dto.BrandDTO;
import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.service.BrandService;
import com.vengeang.phoneshop.service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand= Mapper.toBrand(brandDTO);
        brand=brandService.create(brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }
}
