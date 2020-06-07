package com.education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
public class JpaProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "expired_date")
    private Date expiredDate;
    @Column(name = "price")
    private int price;
    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;

    public JpaProduct() {
    }

    public JpaProduct(String name, Date expiredDate, int price, Type type) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.price = price;
        this.type = type;
    }
    public JpaProduct(int id, String name, Date expiredDate, int price, Type type) {
        this.id = id;
        this.name = name;
        this.expiredDate = expiredDate;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "JpaProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expiredDate=" + expiredDate +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaProduct that = (JpaProduct) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(expiredDate, that.expiredDate) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expiredDate, price, type);
    }
}
