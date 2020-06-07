package com.education.entity;

import java.util.Date;
import java.util.Objects;

public class JdbcProduct {
    private int id;
    private String name;
    private Date expiredDate;
    private int price;
    private int typeId;

    public JdbcProduct(int id, String name, Date expiredDate, int price, int typeId) {
        this.id = id;
        this.name = name;
        this.expiredDate = expiredDate;
        this.price = price;
        this.typeId = typeId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JdbcProduct that = (JdbcProduct) o;
        return id == that.id &&
                price == that.price &&
                typeId == that.typeId &&
                Objects.equals(name, that.name) &&
                Objects.equals(expiredDate, that.expiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expiredDate, price, typeId);
    }
}
