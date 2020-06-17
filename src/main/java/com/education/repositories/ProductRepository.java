package com.education.repositories;

import com.education.entity.Product;
import com.education.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);

    List<Product> findByExpiredDate(Date expiredDate);

    List<Product> findByTypeName(String name);

    List<Product> findByExpiredDateBefore(Date expiredDate);

    List<Product> findByPriceIsLessThan(int price);

    List<Product> findByType(Type type);
}
