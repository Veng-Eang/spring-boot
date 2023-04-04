package com.vengeang.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class PriceDTO {
    @DecimalMin(value = "0.00001",message = "price must be greater than 0 !!")
    private BigDecimal price;
}