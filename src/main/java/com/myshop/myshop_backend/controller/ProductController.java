package com.myshop.myshop_backend.controller;

import com.myshop.myshop_backend.dto.request.ProductRequest;
import com.myshop.myshop_backend.dto.response.ProductResponse;
import com.myshop.myshop_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 🟢 Yangi mahsulot yaratish
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> createProduct(
            @ModelAttribute ProductRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        ProductResponse response = productService.createProduct(request, file);
        return ResponseEntity.ok(response);
    }




    // 🟢 Barcha mahsulotlarni olish
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 🟢 ID orqali mahsulot olish
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // 🟢 Mahsulotni yangilash
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id,
            @RequestPart("request") ProductRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        ProductResponse updatedProduct = productService.updateProduct(id, request, file);
        return ResponseEntity.ok(updatedProduct);
    }

    // 🔴 Mahsulotni o‘chirish
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Mahsulot muvaffaqiyatli o‘chirildi!");
    }
}
