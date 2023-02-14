package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.repositories.BrandRepository;
import com.vengeang.phoneshop.service.BrandService;
import com.vengeang.phoneshop.service.util.PageUtil;
import com.vengeang.phoneshop.spec.BrandFilter;
import com.vengeang.phoneshop.spec.BrandSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Override
    public Page<Brand> getBrands(Map<String,String> params){
        BrandFilter brandFilter=new BrandFilter();
        if (params.containsKey("name")){
            brandFilter.setName(params.get("name"));
        }
        if(params.containsKey("id")){
            brandFilter.setId(Integer.parseInt(params.get("id")));
        }
        int pageLimit= PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit=Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber=PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber=Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        BrandSpec brandSpec=new BrandSpec(brandFilter);
        Pageable pageable=PageUtil.getPageable(pageNumber,pageLimit);
        return brandRepository.findAll(brandSpec, pageable);
    }

}
