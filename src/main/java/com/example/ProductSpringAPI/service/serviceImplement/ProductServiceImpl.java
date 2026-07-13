package com.example.ProductSpringAPI.service.serviceImplement;

import com.example.ProductSpringAPI.dto.request.ProductRequest;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
import com.example.ProductSpringAPI.dto.response.Productinfo;
import com.example.ProductSpringAPI.dto.response.UserProductResponse;
import com.example.ProductSpringAPI.entity.Product;
import com.example.ProductSpringAPI.entity.User;
import com.example.ProductSpringAPI.exception.ProductNotFound;
import com.example.ProductSpringAPI.exception.UserNotFound;
import com.example.ProductSpringAPI.repository.ProductRepository;
import com.example.ProductSpringAPI.repository.UserRepository;
import com.example.ProductSpringAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final String uploadDir = "uploads/";


    @Override
    public ProductResponse create(ProductRequest productRequest, MultipartFile file) throws IOException {

        User user = userRepository.findById(productRequest.getUserid())
                .orElseThrow(() -> new UserNotFound("User not found!!!"));

        String fileName= file.getOriginalFilename();
        String fileUrl= UUID.randomUUID().toString()+"_"+fileName;
        Path path= Paths.get("uploads");
        String imageUrl="http://localhost:8080/uploads/"+fileUrl;
        if (!Files.exists(path)){
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(),path.resolve(fileUrl));

        Product product=new Product();
        product.setImage(imageUrl);
        product.setProName(productRequest.getProName());
        product.setQty(productRequest.getQty());
        product.setPrice(productRequest.getPrice());
        product.setUser(user);
        product=productRepository.save(product);


        ProductResponse productResponse=new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProName(product.getProName());
        productResponse.setQty(product.getQty());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());

        productResponse.setUserId(product.getUser().getId());
        productResponse.setInsertedByUsername(product.getUser().getName());
        productResponse.setInsertedByEmail(product.getUser().getEmail());

        productResponse.setCreatedAt(product.getCreatedAt());

        return productResponse;
    }
    @Override
    public List<UserProductResponse> getData() {
        List<Product> products = productRepository.findAll();

        // group products by user id, preserving insertion order
        Map<Long, List<Product>> groupedByUser = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getUser().getId(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        List<UserProductResponse> result = new ArrayList<>();

        for (List<Product> userProducts : groupedByUser.values()) {
            User user = userProducts.get(0).getUser();

            UserProductResponse groupResponse = new UserProductResponse();
            groupResponse.setUserId(user.getId());
            groupResponse.setInsertedByUsername(user.getName());
            groupResponse.setInsertedByEmail(user.getEmail());
            groupResponse.setProduct(
                    userProducts.stream()
                            .map(this::mapToProductInfo)
                            .collect(Collectors.toList())
            );

            result.add(groupResponse);
        }

        return result;
    }

    private Productinfo mapToProductInfo(Product product) {
        Productinfo info = new Productinfo();
        info.setId(product.getId());
        info.setProName(product.getProName());
        info.setQty(product.getQty());
        info.setPrice(product.getPrice());
        info.setImage(product.getImage());
        info.setCreatedAt(product.getCreatedAt());
        info.setUpdatedAt(product.getUpdatedAt());
        return info;
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product not found!!!"));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProName(product.getProName());
        productResponse.setQty(product.getQty());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());

        productResponse.setUserId(product.getUser().getId());
        productResponse.setInsertedByUsername(product.getUser().getName());
        productResponse.setInsertedByEmail(product.getUser().getEmail());

        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());

        productRepository.deleteById(id);

        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest, MultipartFile file) throws IOException {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new ProductNotFound("Product not found!!!"));

        User user = userRepository.findById(productRequest.getUserid())
                .orElseThrow(() -> new UserNotFound("User id not found"));

        String fileName= file.getOriginalFilename();
        String fileUrl= UUID.randomUUID().toString()+"_"+fileName;
        Path path= Paths.get("uploads");
        String imageUrl="http://localhost:8080/uploads/"+fileUrl;
        Files.copy(file.getInputStream(),path.resolve(fileUrl));
        product.setImage(imageUrl);
        product.setProName(productRequest.getProName());
        product.setQty(productRequest.getQty());
        product.setPrice(productRequest.getPrice());
        product.setUser(user);
        product=productRepository.save(product);

        ProductResponse productResponse=new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProName(product.getProName());
        productResponse.setQty(product.getQty());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());

        productResponse.setUserId(product.getUser().getId());
        productResponse.setInsertedByUsername(product.getUser().getName());
        productResponse.setInsertedByEmail(product.getUser().getEmail());

        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());

        return productResponse;
    }
}