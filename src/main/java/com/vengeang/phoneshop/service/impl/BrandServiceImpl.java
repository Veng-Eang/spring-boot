package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.repositories.BrandRepository;
import com.vengeang.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
//          use normal exception
//        return brandRepository.findById(id)    // => ប្រើ Error Exception ដែលគេផ្ដល់អោយស្រាប់
//                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format("Brand with id=%d not found",id)));
//        use custom ApiException
        return brandRepository.findById(id)
                .orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,String.format("Brand with id= %d not found",id)));// ប្រើ ApiException constructor សម្រាប់ Handle error
    }

    @Override
    public Brand update(Integer id,Brand brand) {
        Brand brandUpdate = getById(id);
        brandUpdate.setName(brand.getName());
        return brandRepository.save(brandUpdate);
    }
}
