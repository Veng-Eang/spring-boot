package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.entities.Color;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.repositories.ColorRepository;
import com.vengeang.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,String.format("Color with id= %d not found",id))
        );
    }
}
