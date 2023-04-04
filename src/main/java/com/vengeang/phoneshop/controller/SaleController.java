package com.vengeang.phoneshop.controller;

import com.vengeang.phoneshop.dto.SaleDTO;
import com.vengeang.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {
    private final SaleService saleService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SaleDTO saleDTO){
        saleService.sell(saleDTO);
        return ResponseEntity.ok("success!!");
    }
}
