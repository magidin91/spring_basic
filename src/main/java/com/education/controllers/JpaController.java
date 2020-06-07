package com.education.controllers;

import com.education.entity.JpaProduct;
import com.education.entity.Type;
import com.education.jpa.ProductJpaRepository;
import com.education.jpa.TypeJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    final ProductJpaRepository productJpaRepository;
    final TypeJpaRepository typeJpaRepository;

    public JpaController(ProductJpaRepository productJpaRepository, TypeJpaRepository typeJpaRepository) {
        this.productJpaRepository = productJpaRepository;
        this.typeJpaRepository = typeJpaRepository;
    }

    @GetMapping("/products")
    public List<JpaProduct> getProducts() {
        return productJpaRepository.findAll();
    }

    @GetMapping("/types")
    public List<Type> getTypes() {
        return typeJpaRepository.findAll();
    }

    @PostMapping("/save/product")
    public JpaProduct addProduct(@RequestBody JpaProduct product) {
        return productJpaRepository.save(product);
    }

    @PostMapping("/save/type")
    public Type addType(@RequestBody Type type) {
        return typeJpaRepository.save(type);
    }

    @PostMapping("/product/byname")
    List<JpaProduct> findByName(@RequestBody String name) {
        return productJpaRepository.findByName(name);
    }
}
