package com.education.jpa;

import com.education.entity.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<JpaProduct, Integer> {

    List<JpaProduct> findByName(String name);

    List<JpaProduct> findByExpiredDate(Date expiredDate);

    List<JpaProduct> findByTypeName(String name);

    List<JpaProduct> findByExpiredDateBefore(Date expiredDate);

    List<JpaProduct> findByPriceIsLessThan(int price);

}
