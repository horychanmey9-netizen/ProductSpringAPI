package com.example.ProductSpringAPI.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class UserProductResponse {
    private Long userId;
    private String insertedByUsername;
    private String insertedByEmail;
    private List<Productinfo> product;
}