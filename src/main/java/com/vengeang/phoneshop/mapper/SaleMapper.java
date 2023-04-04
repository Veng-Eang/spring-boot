package com.vengeang.phoneshop.mapper;

import com.vengeang.phoneshop.dto.ProductSoldDTO;
import com.vengeang.phoneshop.dto.SaleDTO;
import com.vengeang.phoneshop.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {SaleDTO.class})
public interface SaleMapper {
    Sale toSale(SaleDTO saleDTO);
}
