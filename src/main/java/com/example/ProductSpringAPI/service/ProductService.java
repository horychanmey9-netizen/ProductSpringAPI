package com.example.ProductSpringAPI.service;

import com.example.ProductSpringAPI.dto.request.ProductRequest;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
import com.example.ProductSpringAPI.dto.response.UserProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest, MultipartFile file)throws IOException;
    List<UserProductResponse> getData();
    ProductResponse deleteProduct(Long id);
    ProductResponse updateProduct(Long id,ProductRequest productRequest, MultipartFile file)throws IOException;
}
