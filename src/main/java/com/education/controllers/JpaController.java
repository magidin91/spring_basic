package com.education.controllers;

import com.education.entity.Product;
import com.education.entity.Type;
import com.education.jpa.ProductRepository;
import com.education.jpa.TypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    final ProductRepository productRepository;
    final TypeRepository typeRepository;

    public JpaController(ProductRepository productRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/types")
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    @PostMapping("/save/product")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PostMapping("/save/type")
    public Type addType(@RequestBody Type type) {
        return typeRepository.save(type);
    }

    @PostMapping("/product/byname")
    List<Product> findByName(@RequestBody String name) {
        return productRepository.findByName(name);
    }
}
