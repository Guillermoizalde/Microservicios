package com.guillermo.springcloud.msv.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.guillermo.springcloud.msv.items.models.Product;

@FeignClient(url = "http://localhost:8080", name = "msv-products")
public interface ProductClientFeign {

    @GetMapping("/api/products")
    List<Product> findAll();
    
    @GetMapping("/api/products/{id}")
    Product details(@PathVariable Long id);

}
