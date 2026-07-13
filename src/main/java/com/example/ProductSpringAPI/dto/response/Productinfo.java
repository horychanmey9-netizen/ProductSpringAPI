package com.example.ProductSpringAPI.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Productinfo {
    private Long id;
    private String proName;
    private Integer qty;
    private Double price;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}