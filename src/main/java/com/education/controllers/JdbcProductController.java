package com.education.controllers;

import com.education.entity.JdbcProduct;
import com.education.jdbc.JdbcRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/product")
public class JdbcProductController {
    private final JdbcRepository jdbcRepository;

    public JdbcProductController(JdbcRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    @GetMapping
    public List<JdbcProduct> getProducts() {
        return jdbcRepository.getProducts();
    }

    @PostMapping
    public int addProduct(@RequestBody JdbcProduct jdbcProduct) {
        return jdbcRepository.addProduct(jdbcProduct);
    }

    @GetMapping("/count")
    public int count() {
        return jdbcRepository.count();
    }

    @GetMapping("/{price}")
    public List<JdbcProduct> getProductsPriceIsLower(@PathVariable("price") Integer price) {
        return jdbcRepository.getProductsPriceIsLower(price);
    }

    @GetMapping("/maxprice")
    public List<JdbcProduct> getMaxPriceProduct() {
        return jdbcRepository.getMaxPriceProduct();
    }
}
