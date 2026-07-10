package com.example.ProductSpringAPI.controller;

import com.example.ProductSpringAPI.dto.request.UserRequest;
import com.example.ProductSpringAPI.dto.response.UserResponse;
import com.example.ProductSpringAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@ModelAttribute UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping("/all")
    public List<UserResponse> getData() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        userService.deleteById(id);
        return "Data deleted successfully.";
    }

    @PutMapping("/{id}")
    public UserResponse updateBook(
            @PathVariable Long id,
            @ModelAttribute UserRequest userRequest) {

        return userService.update(id, userRequest);
    }
}