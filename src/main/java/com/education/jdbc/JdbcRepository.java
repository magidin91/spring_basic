package com.education.jdbc;

import com.education.entity.JdbcProduct;
import com.education.entity.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from product", java.lang.Integer.class);
    }

    public List<JdbcProduct> getProducts() {
        return jdbcTemplate.query("select* from product", (rs, rowNum) -> new JdbcProduct(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("expired_date"),
                rs.getInt("price"),
                rs.getInt("type_id")
        ));
    }

    public List<Type> getTypes() {
        return jdbcTemplate.query("select* from type", (rs, rowNum) -> new Type(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }

    public int addProduct(JdbcProduct jdbcProduct) {
        return jdbcTemplate.update(String.format(
                "insert into product (name, expired_date, price, type_id) " +
                        "values ('%s', '%s', %d, %d)",
                jdbcProduct.getName(), jdbcProduct.getExpiredDate(), jdbcProduct.getPrice(), jdbcProduct.getTypeId()));
    }

    public int addType(Type type) {
        return jdbcTemplate.update(String.format(
                "insert into type (name) values ('%s') ON CONFLICT (name) DO NOTHING", type.getName()));
    }

    /**
     * Returns products with a price less than or equal to the specified price
     */
    public List<JdbcProduct> getProductsPriceIsLower(int price) {
        return jdbcTemplate.query(String.format("select* from product where price<=%s", price),
                (rs, rowNum) -> new JdbcProduct(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("expired_date"),
                        rs.getInt("price"),
                        rs.getInt("type_id")
                ));
    }

    /**
     * Returns products of a specific type
     */
    public List<JdbcProduct> getProductByType(String type) {
        return jdbcTemplate.query(String.format(
                "Select* from product as p Inner join type as t " +
                        "On p.type_id = t.id where t.name = '%s'", type),
                (rs, rowNum) -> new JdbcProduct(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("expired_date"),
                        rs.getInt("price"),
                        rs.getInt("type_id")
                ));
    }

    /**
     * Returns products with max price
     */
    public List<JdbcProduct> getMaxPriceProduct() {
        return jdbcTemplate.query((
                        "Select* from product where price = (Select Max(price) from product)"),
                (rs, rowNum) -> new JdbcProduct(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("expired_date"),
                        rs.getInt("price"),
                        rs.getInt("type_id")
                ));
    }

    /**
     * Outputs the type of products that are available and less than N pieces
     */
    public List<String> getTypesCountIsLower(int count) {
        return jdbcTemplate.query(
                String.format("Select t.name from product as p Inner join type as t On p.type_id = t.id " +
                        "Group by t.name having count(*) < %d", count),
                (rs, rowNum) -> rs.getString("name"));
    }
}
