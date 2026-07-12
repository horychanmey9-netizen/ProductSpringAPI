package com.example.ProductSpringAPI.controller;

import com.example.ProductSpringAPI.dto.request.UserRequest;
import com.example.ProductSpringAPI.dto.response.ApiResponse;
import com.example.ProductSpringAPI.dto.response.UserResponse;
import com.example.ProductSpringAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 Created status
    public ApiResponse<UserResponse> create(@RequestBody UserRequest userRequest) {
        UserResponse data = userService.create(userRequest);
        return new ApiResponse<>("User created successfully", 201, data);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getData(){
        return new ApiResponse<>("Get data successfully", 200, userService.getData());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> deleteData(@PathVariable Long id) {
        UserResponse deletedData = userService.deleteById(id);
        return new ApiResponse<>("User deleted successfully", 200, deletedData);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest) {

        UserResponse data = userService.update(id, userRequest);
        return new ApiResponse<>("User updated successfully", 200, data);
    }
}