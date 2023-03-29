package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Long id);
    Brand update(Long id, Brand brand);
    Page<Brand> getBrands(Map<String,String> params);
}
