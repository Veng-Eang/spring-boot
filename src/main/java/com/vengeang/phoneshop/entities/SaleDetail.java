package com.vengeang.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_sale")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "sale_amount")
    private BigDecimal amount;
    @Column(name = "date_sold")
    private LocalDateTime soldDate;
    @Column(name = "sale_unit")
    private Integer unit;
}
