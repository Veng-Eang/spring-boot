package com.vengeang.phoneshop.mapper;

import com.vengeang.phoneshop.dto.ModelDTO;
import com.vengeang.phoneshop.entities.Brand;
import com.vengeang.phoneshop.entities.Model;
import com.vengeang.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE= Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand",source = "brandId")
    Model toModel(ModelDTO dto);
    @Mapping(target = "brandId",source = "brand.id")
    @Mapping(target = "brandName",source = "brand.name")
    ModelDTO toModelDTO(Model entity);

}
