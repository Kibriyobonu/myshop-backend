package com.myshop.myshop_backend.service;

import com.myshop.myshop_backend.dto.ProductDto;
import com.myshop.myshop_backend.dto.request.ProductRequest;

import java.io.IOException;
import java.util.UUID;

public interface ProductService {
    ProductDto create(ProductRequest request) throws IOException;
    ProductDto getById(UUID id);
}
