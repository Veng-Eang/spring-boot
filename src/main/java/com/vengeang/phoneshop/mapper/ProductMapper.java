package com.vengeang.phoneshop.mapper;

import com.vengeang.phoneshop.dto.ProductDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.entities.ProductImportHistory;
import com.vengeang.phoneshop.service.ColorService;
import com.vengeang.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "pricePerUnit",source = "importDTO.importPrice")
    @Mapping(target = "product",source = "product")
    @Mapping(target = "id",ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO importDTO,Product product);
}
