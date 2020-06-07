package com.education.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Type of product (Cheese, water, milk etc)
 */
@Entity
@Table(name = "type")
public class Type {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_id_seq")
    @SequenceGenerator(name = "type_id_seq", sequenceName = "type_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "name")
    private String name;

    public Type() {
    }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id &&
                Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


