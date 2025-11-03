package com.guillermo.springcloud.msv.items.services;

import java.util.List;
import java.util.Optional;

import com.guillermo.springcloud.msv.items.models.Item;

public interface ItemService {

    List<Item> findAll();
    
    Optional<Item> findById(Long id);

}
