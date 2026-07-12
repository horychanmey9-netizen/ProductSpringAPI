package com.example.ProductSpringAPI.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String email;

    private LocalDateTime createdAt;

    // បន្ថែម Annotation នេះដើម្បីឱ្យវាលាក់ខ្លួនឯងនៅពេលតម្លៃជា null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;

//    private List<String> productName;
}
