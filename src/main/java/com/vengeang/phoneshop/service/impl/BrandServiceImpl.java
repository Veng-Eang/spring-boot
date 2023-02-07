package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.repositories.BrandRepository;
import com.vengeang.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }
}
