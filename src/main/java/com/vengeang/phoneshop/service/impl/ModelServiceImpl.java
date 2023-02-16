package com.vengeang.phoneshop.service.impl;
import com.vengeang.phoneshop.entities.Model;
import com.vengeang.phoneshop.mapper.ModelEntityMapper;
import com.vengeang.phoneshop.repositories.ModelRepository;
import com.vengeang.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelEntityMapper modelEntityMapper;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> findModelByBrandId(Integer brandId) {
        return modelRepository.findModelByBrand_Id(brandId);
    }

}
