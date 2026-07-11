package com.example.ProductSpringAPI.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String proName;
    private Integer qty;
    private Double price;
    private String image;
    private Long Userid;
}