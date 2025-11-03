package com.guillermo.springcloud.msv.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.guillermo.springcloud.msv.products.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    

}
