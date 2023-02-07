package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.repositories.BrandRepository;
import com.vengeang.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
        Optional<Brand> byId = brandRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand : with id =" + id + " not found");
        }
    }
}
