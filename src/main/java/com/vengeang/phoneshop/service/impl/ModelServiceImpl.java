package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.dto.ModelDTO;
import com.vengeang.phoneshop.entities.Model;
import com.vengeang.phoneshop.mapper.ModelMapper;
import com.vengeang.phoneshop.repositories.ModelRepository;
import com.vengeang.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
