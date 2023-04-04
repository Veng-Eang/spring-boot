package com.vengeang.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tbl_product",
uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name",unique = true)
    private String name;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "available_unit")
    private Integer availableUnit;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @DecimalMin(value = "0.00001",message = "price must be greater than 0 !!")
    @Column(name = "sale_price")
    private BigDecimal salePrice;
}
