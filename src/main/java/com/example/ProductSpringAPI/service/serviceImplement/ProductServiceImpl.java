package com.example.ProductSpringAPI.service.serviceImplement;

import com.example.ProductSpringAPI.dto.request.ProductRequest;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        productResponse.setInsertedByUsername(product.getUser().getName());
        productResponse.setInsertedByEmail(product.getUser().getEmail());
        return productResponse;
    }
    @Override
    public List<ProductResponse> getData() {
        List<Product> products=productRepository.findAll();
        List<ProductResponse> productResponses=new ArrayList<>();
        for (Product product:products){
            ProductResponse productResponse =new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setProName(product.getProName());
            productResponse.setQty(product.getQty());
            productResponse.setPrice(product.getPrice());
            productResponse.setImage(product.getImage());
            productResponse.setInsertedByUsername(product.getUser().getName());
            productResponse.setInsertedByEmail(product.getUser().getEmail());
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new ProductNotFound("Book not found!!!"));
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest, MultipartFile file) throws IOException {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new ProductNotFound("Book not found!!!"));

        String fileName= file.getOriginalFilename();
        String fileUrl= UUID.randomUUID().toString()+"_"+fileName;
        Path path= Paths.get("uploads");
        String imageUrl="http://localhost:8080/uploads/"+fileUrl;
        Files.copy(file.getInputStream(),path.resolve(fileUrl));
        product.setImage(imageUrl);
        product.setProName(productRequest.getProName());
        product.setQty(productRequest.getQty());
        product.setPrice(productRequest.getPrice());
        product=productRepository.save(product);

        ProductResponse productResponse=new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProName(product.getProName());
        productResponse.setQty(product.getQty());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());
        productResponse.setInsertedByUsername(product.getUser().getName());
        productResponse.setInsertedByEmail(product.getUser().getEmail());
        return productResponse;
    }
}
