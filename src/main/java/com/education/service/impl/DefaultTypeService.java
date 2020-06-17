package com.education.service.impl;

import com.education.entity.Product;
import com.education.entity.Type;
import com.education.exceptions.*;
import com.education.repositories.ProductRepository;
import com.education.repositories.TypeRepository;
import com.education.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTypeService implements TypeService {
    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;

    public DefaultTypeService(ProductRepository productRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type findById(Object id) {
        Type type;
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(id, "Идентификатор объекта не может быть null");
        Integer parsedId = ExceptionThrower.ifIdFormatIsWrongThrowEntityIllegalArgumentException(id);
        type = typeRepository.findById(parsedId).orElse(null);
        ExceptionThrower.ifNullThrowEntityNotFoundException(type, Type.Type_Name, parsedId);
        return type;
    }

    public Type create(Type type) {
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type, "Создаваемый объект не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type.getId(), "Идентификатор объекта не может быть null");
        Type existedType = typeRepository.findById(type.getId()).orElse(null);
        ExceptionThrower.ifObjectIsNotNullThrowEntityAlreadyExistsException(existedType, Type.Type_Name, type.getId());
        return typeRepository.save(type);
    }

    @Override
    public Type update(Type type) {
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type, "Создаваемый объект не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type.getId(), "Идентификатор объекта не может быть null");
        Type existedType = typeRepository.findById(type.getId()).orElse(null);
        ExceptionThrower.ifNullThrowEntityNotFoundException(existedType, Product.Type_Name, type.getId());
        return typeRepository.save(type);
    }

    /**
     * Удаляет тип.
     * Если у типа есть связанные продукты, кидает EntityHasDetailsException.
     */
    public void delete(Object id) {
        Type type = findById(id);
        List<Product> products = productRepository.findByType(type);
        ExceptionThrower.ifSizeMore0ThrowEntityHasDetailsException(products.size(), Product.Type_Name, type.getId());
        typeRepository.delete(type);
    }
}
