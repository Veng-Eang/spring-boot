package com.vengeang.phoneshop.service.util;

import com.vengeang.phoneshop.dto.BrandDTO;
import com.vengeang.phoneshop.entities.Brand;

public class Mapper {
    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO=new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }
    public static Brand toBrand(BrandDTO brandDTO){
        Brand brand=new Brand();
        brand.setName(brandDTO.getName());
        return brand;
    }
}
