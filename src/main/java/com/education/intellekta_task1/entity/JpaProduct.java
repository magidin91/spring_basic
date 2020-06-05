package com.education.intellekta_task1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@NoArgsConstructor
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
}
