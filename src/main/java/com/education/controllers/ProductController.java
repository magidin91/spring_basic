package com.education.controllers;

import com.education.entity.Product;
import com.education.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    Product findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //http статус запроса
    Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        productService.delete(id);
    }
}