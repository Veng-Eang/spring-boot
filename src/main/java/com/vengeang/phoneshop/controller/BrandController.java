package com.vengeang.phoneshop.controller;


import com.vengeang.phoneshop.dto.BrandDTO;
import com.vengeang.phoneshop.dto.PageDTO;
import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.mapper.BrandMapper;
import com.vengeang.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand= BrandMapper.INSTANCE.toBrand(brandDTO);
        brand=brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }
    @GetMapping("{id}")
    public  ResponseEntity<?> getOne(@PathVariable("id") Integer brand_id){
        Brand brand = brandService.getById(brand_id);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }
    @PutMapping("{id}")
    public  ResponseEntity<?> update(@PathVariable("id") Integer brand_id,@RequestBody BrandDTO brandDTO){
        Brand brandUpdate=BrandMapper.INSTANCE.toBrand(brandDTO);
        brandUpdate=brandService.update(brand_id,brandUpdate);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brandUpdate));
    }
    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam Map<String,String> params){
        Page<Brand> brands = brandService.getBrands(params);
        PageDTO pageDTO= new PageDTO(brands);
//        List<BrandDTO> collect = brandService.getBrands(params)
//                .stream()
//                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
//                .collect(Collectors.toList());
        return ResponseEntity.ok(pageDTO);
    }
}
