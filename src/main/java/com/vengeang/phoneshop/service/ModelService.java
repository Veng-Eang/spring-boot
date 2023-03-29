package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.dto.ModelDTO;
import com.vengeang.phoneshop.entities.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {
    Model save(Model model);
    List<Model> findModelByBrandId(Long brandId);
    Model getById(Long id);
}
