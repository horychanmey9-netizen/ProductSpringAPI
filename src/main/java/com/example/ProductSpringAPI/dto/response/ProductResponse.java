package com.example.ProductSpringAPI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String proName;
    private Integer qty;
    private Double price;
    private String image;

    private String insertedByUsername;
    private String insertedByEmail;
}
