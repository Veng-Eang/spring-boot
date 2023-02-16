package com.vengeang.phoneshop.controller;

import com.vengeang.phoneshop.dto.ModelDTO;
import com.vengeang.phoneshop.entities.Model;
import com.vengeang.phoneshop.mapper.ModelMapper;
import com.vengeang.phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("models")
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
        Model model = modelMapper.toModel(modelDTO);
        Model saveModel = modelService.save(model);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDTO(saveModel));
    }
}
