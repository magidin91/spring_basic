package com.education.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    public static String Type_Name = "Продукт";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "expired_date")
    private Date expiredDate;

    @Column(name = "price")
    private int price;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;

    public Product() {
    }

    public Product(String name, Date expiredDate, int price, Type type) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.price = price;
        this.type = type;
    }

    public Product(Integer id, String name, Date expiredDate, int price, Type type) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id.equals(that.id) &&
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
