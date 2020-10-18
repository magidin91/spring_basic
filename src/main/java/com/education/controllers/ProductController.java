package com.education.controllers;

import com.education.entity.Product;
import com.education.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * После аутентификации вызывается doFilter-> hasPermission и проверяет права юзера.
     * Если у него есть право чтения ('product', 'read'), то вызывается метод findAll
     */
    @GetMapping
    @PreAuthorize("hasPermission('product', 'read')")
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('product', 'readById')")
    Product findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('product', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping
    @PreAuthorize("hasPermission('product', 'update')")
    @ResponseStatus(HttpStatus.OK)
    Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('product', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        productService.delete(id);
    }
}