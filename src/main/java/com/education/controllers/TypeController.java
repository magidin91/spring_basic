package com.education.controllers;

import com.education.entity.Type;
import com.education.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/type")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    List<Type> findAll() {
        return typeService.findAll();
    }

    @GetMapping("/{id}")
    Type findById(@PathVariable String id) {
        return typeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Type update(@RequestBody Type type) {
        return typeService.update(type);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        typeService.delete(id);
    }
}
