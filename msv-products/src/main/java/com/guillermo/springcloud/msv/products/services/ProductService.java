package com.guillermo.springcloud.msv.products.services;

import java.util.List;
import java.util.Optional;

import com.guillermo.springcloud.msv.products.entity.Product;

public interface ProductService {
    
    List<Product> findAll();

    Optional<Product> findById(Long id);

}
