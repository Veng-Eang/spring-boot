package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brand);
    Page<Brand> getBrands(Map<String,String> params);
}
