package com.example.ProductSpringAPI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String proName;
    private Integer qty;
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

}

