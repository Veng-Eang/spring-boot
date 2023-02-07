package com.vengeang.phoneshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse { //use to transfer object ApiException
    private HttpStatus status; //data to transfer
    private String message;// data to transfer
}
