package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.dto.ModelDTO;
import com.vengeang.phoneshop.entities.Model;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {
    Model save(Model model);
}
