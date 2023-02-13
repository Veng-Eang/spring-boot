package com.vengeang.phoneshop.mapper;

import com.vengeang.phoneshop.dto.BrandDTO;
import com.vengeang.phoneshop.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE= Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO brandDTO);
    BrandDTO toBrandDTO(Brand entity);
}
