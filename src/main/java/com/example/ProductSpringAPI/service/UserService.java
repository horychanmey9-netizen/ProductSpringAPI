package com.example.ProductSpringAPI.service;

import com.example.ProductSpringAPI.dto.request.UserRequest;
import com.example.ProductSpringAPI.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAll();

    void deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);
}