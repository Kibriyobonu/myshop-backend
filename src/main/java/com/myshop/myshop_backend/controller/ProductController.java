package com.myshop.myshop_backend.controller;

import com.myshop.myshop_backend.dto.ProductDto;
import com.myshop.myshop_backend.dto.request.ProductRequest;
import com.myshop.myshop_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Swagger will render a form (multipart/form-data)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDto createProduct(@ModelAttribute ProductRequest request) throws Exception {
        return productService.create(request);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable UUID id) {
        return productService.getById(id);
    }
}
