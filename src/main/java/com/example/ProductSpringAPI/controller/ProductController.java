package com.example.ProductSpringAPI.controller;

import com.example.ProductSpringAPI.dto.request.ProductRequest;
import com.example.ProductSpringAPI.dto.response.ApiResponse;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
import com.example.ProductSpringAPI.dto.response.UserProductResponse;
import com.example.ProductSpringAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(
            @ModelAttribute ProductRequest productRequest,
            @RequestParam("file") MultipartFile file) throws IOException {

        ProductResponse data = productService.create(productRequest, file);
        return new ApiResponse<>("Product created successfully", 201, data);
    }
    @GetMapping
    public ApiResponse<List<UserProductResponse>> getData(){
        return new ApiResponse<>("get data successfully", 200, productService.getData());
    }
    @DeleteMapping("/{id}")
    public ApiResponse<ProductResponse> deleteProduct(@PathVariable Long id) {
        ProductResponse deletedData = productService.deleteProduct(id);
        return new ApiResponse<>("Product deleted successfully", 200, deletedData);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(
            @PathVariable Long id,
            @ModelAttribute ProductRequest productRequest,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        ProductResponse data = productService.updateProduct(id, productRequest, file);
        return new ApiResponse<>("Product updated successfully", 200, data);
    }
}