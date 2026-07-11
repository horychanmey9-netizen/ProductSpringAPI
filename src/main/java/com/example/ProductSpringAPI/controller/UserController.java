package com.example.ProductSpringAPI.controller;

import com.example.ProductSpringAPI.dto.request.UserRequest;
import com.example.ProductSpringAPI.dto.response.UserResponse;
import com.example.ProductSpringAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping
    public List<UserResponse> getData() {
        return userService.getData();
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        userService.deleteById(id);
        return "Data deleted successfully.";
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest) {

        return userService.update(id, userRequest);
    }
}