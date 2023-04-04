package com.vengeang.phoneshop.exception;

import com.vengeang.phoneshop.dto.ErrorResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        ErrorResponse errorResponse=new ErrorResponse(e.getStatus(),e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorResponse);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        System.out.println(defaultMessage);
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST,defaultMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleValidException(IllegalArgumentException e){
        System.out.println(e.getMessage());
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> handleValidException(ValidationException e){
        String message = e.getMessage();
        int messageTemplate = message.indexOf("messageTemplate");
        int end = message.indexOf("}");
        System.out.println("message index:"+messageTemplate+"index of:"+end);
        String messageCustom = message.substring(messageTemplate+17, end-1);
        ErrorResponse errorResponse =new ErrorResponse(HttpStatus.BAD_REQUEST, messageCustom);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
