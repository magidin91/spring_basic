package com.education.jpa;

import com.education.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeJpaRepository extends JpaRepository<Type, Integer> {
}

