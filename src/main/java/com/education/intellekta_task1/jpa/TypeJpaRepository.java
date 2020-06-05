package com.education.intellekta_task1.jpa;

import com.education.intellekta_task1.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeJpaRepository extends JpaRepository<Type, Integer> {
}

