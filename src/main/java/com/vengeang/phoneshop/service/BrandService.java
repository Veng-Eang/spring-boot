package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.entities.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brand);
    List<Brand> getBrands();

    List<Brand> getBrands(String name);
}
