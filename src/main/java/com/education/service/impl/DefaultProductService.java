package com.education.service.impl;

import com.education.annotation.Loggable;
import com.education.entity.Product;
import com.education.entity.Type;
import com.education.exceptions.ExceptionThrower;
import com.education.jpa.ProductRepository;
import com.education.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Loggable
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Object id) {
        Product product;
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(id, "Идентификатор объекта не может быть null");
        Integer parsedId = ExceptionThrower.ifIdFormatIsWrongThrowEntityIllegalArgumentException(id);
        product = productRepository.findById(parsedId).orElse(null);
        ExceptionThrower.ifNullThrowEntityNotFoundException(product, Product.Type_Name, parsedId);
        return product;
    }

    public Product create(Product product) {
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(product, "Создаваемый объект не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(product.getId(), "Идентификатор объекта не может быть null");
        Type type = product.getType();
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type, "Поле Type не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type.getId(), "Идентификатор типа продукта не может быть null");
        Product existedProduct = productRepository.findById(product.getId()).orElse(null);
        ExceptionThrower.ifObjectIsNotNullThrowEntityAlreadyExistsException(existedProduct, Product.Type_Name, product.getId());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(product, "Создаваемый  объект не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(product.getId(), "Идентификатор объекта не может быть null");
        Type type = product.getType();
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type, "Поле Type не может быть null");
        ExceptionThrower.ifNullThrowEntityIllegalArgumentException(type.getId(), "Идентификатор типа продукта не может быть null");
        Product existedProduct = productRepository.findById(product.getId()).orElse(null);
        ExceptionThrower.ifNullThrowEntityNotFoundException(existedProduct, Product.Type_Name, product.getId());
        return productRepository.save(product);
    }

    public void delete(Object id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
