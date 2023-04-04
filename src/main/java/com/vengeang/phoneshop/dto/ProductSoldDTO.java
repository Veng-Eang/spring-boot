package com.vengeang.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProductSoldDTO {
    @NotNull(message = "Product id can't be null! ")
    private Long productId;

    @Min(value = 1,message = "number of unit be greater than 0!!")
    @NotNull(message = "Number of unit can't be null!!")
    private Integer numberOfUnit;
}
