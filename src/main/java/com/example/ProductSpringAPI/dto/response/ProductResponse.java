package com.example.ProductSpringAPI.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String proName;
    private Integer qty;
    private Double price;
    private String image;
}
