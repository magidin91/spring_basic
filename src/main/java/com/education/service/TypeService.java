package com.education.service;

import com.education.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    Type findById(Object id);

    Type create(Type type);

    Type update(Type type);

    void delete(Object id);
}
