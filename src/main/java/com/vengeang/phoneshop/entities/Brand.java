package com.vengeang.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;
    @Column(name = "brand_name")
    private String name;
}
