package com.example.ProductSpringAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude; // នាំចូល Library នេះ
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String gender;
    private Integer age;

    private LocalDateTime createdAt;

    // បន្ថែម Annotation នេះដើម្បីឱ្យវាលាក់ខ្លួនឯងនៅពេលតម្លៃជា null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;
}