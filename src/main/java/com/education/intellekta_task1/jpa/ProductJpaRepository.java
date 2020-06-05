package com.education.intellekta_task1.jpa;

import com.education.intellekta_task1.entity.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<JpaProduct, Integer> {

    List<JpaProduct> findByName(String name);
}
