package com.myshop.myshop_backend.service;

import com.myshop.myshop_backend.dto.request.ProductRequest;
import com.myshop.myshop_backend.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request, MultipartFile file) throws IOException;

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(UUID id);

    ProductResponse updateProduct(UUID id, ProductRequest request, MultipartFile file) throws IOException;

    void deleteProduct(UUID id);


}
