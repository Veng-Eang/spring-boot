package com.vengeang.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    @NotEmpty(message = "product can't empty!!")
    @NotNull(message = "product can't be null !!")
    private List<ProductSoldDTO> products;
    @NotNull(message = "date can't null")
    private LocalDateTime soldDate;
}
