package com.example.ProductSpringAPI.controller;

import com.example.ProductSpringAPI.dto.request.ProductRequest;
import com.example.ProductSpringAPI.dto.response.ApiResponse;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
import com.example.ProductSpringAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
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
    public ProductResponse create(@ModelAttribute ProductRequest productRequest, @RequestParam("file") MultipartFile file)throws IOException {
        return productService.create(productRequest,file);
    }
    @GetMapping
    public ApiResponse<List<ProductResponse>> getData(){
        return new ApiResponse<>("get data successfully",200,productService.getData());
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,@ModelAttribute ProductRequest productRequest,@RequestParam("file") MultipartFile file)throws IOException{
        return productService.updateProduct(id,productRequest, file);
    }
}