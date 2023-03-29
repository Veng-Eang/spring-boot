package com.vengeang.phoneshop.service;

import com.vengeang.phoneshop.entities.Color;

public interface ColorService {
    Color create(Color color);
    Color getById(Long id);
}
