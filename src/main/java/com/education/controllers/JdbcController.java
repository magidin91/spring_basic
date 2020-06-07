package com.education.controllers;

import com.education.entity.JdbcProduct;
import com.education.entity.Type;
import com.education.jdbc.JdbcRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    private final JdbcRepository jdbcRepository;

    public JdbcController(JdbcRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    @GetMapping("/products")
    public List<JdbcProduct> getProducts() {
        return jdbcRepository.getProducts();
    }

    @GetMapping("/types")
    List<Type> getTypes() {
        return jdbcRepository.getTypes();
    }

    @PostMapping("/save/product")
    public int addProduct(@RequestBody JdbcProduct jdbcProduct) {
        return jdbcRepository.addProduct(jdbcProduct);
    }

    @PostMapping("/save/type")
    public int addType(@RequestBody Type type) {
        return jdbcRepository.addType(type);
    }

    @GetMapping("/products/count")
    public int count() {
        return jdbcRepository.count();
    }

    @PostMapping("/products/priceislower")
    public List<JdbcProduct> getProductsPriceIsLower(@RequestBody String number) {
        return jdbcRepository.getProductsPriceIsLower(Integer.parseInt(number));
    }

    @PostMapping("/products/bytype")
    public List<JdbcProduct> getProductByType(@RequestBody String type) {
        return jdbcRepository.getProductByType(type);
    }

    @GetMapping("/products/maxprice")
    public List<JdbcProduct> getMaxPriceProduct() {
        return jdbcRepository.getMaxPriceProduct();
    }

    @PostMapping("/types/countislower")
    public List<String> getTypesCountIsLower(@RequestBody String number) {
        return jdbcRepository.getTypesCountIsLower(Integer.parseInt(number));
    }
}
