package com.vengeang.phoneshop.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor// ចាំបាច់ត្រួវតែមាន Constructor
public class ApiException extends RuntimeException{ //=> class សម្រាប់ Custom constructor ដោយខ្លួនយើង
    private final HttpStatus status;
    private final String message;
}
