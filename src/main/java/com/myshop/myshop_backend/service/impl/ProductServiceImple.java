package com.myshop.myshop_backend.service.impl;

import com.myshop.myshop_backend.dto.ProductDto;
import com.myshop.myshop_backend.dto.request.ProductRequest;
import com.myshop.myshop_backend.entity.Category;
import com.myshop.myshop_backend.entity.Product;
import com.myshop.myshop_backend.repository.CategoryRepository;
import com.myshop.myshop_backend.repository.ProductRepository;
import com.myshop.myshop_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImple implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // use property from application.properties
    @Value("${upload.folder:uploads/products/}")
    private String uploadFolder;

    @Override
    public ProductDto create(ProductRequest request) throws IOException {

        // Validate & load category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Ensure upload folder exists
        File uploadDir = new File(uploadFolder);
        if (!uploadDir.exists()) {
            boolean ok = uploadDir.mkdirs();
            if (!ok) throw new IOException("Could not create upload folder: " + uploadDir.getAbsolutePath());
        }

        // Save file if present
        String storedFileName = null;
        MultipartFile file = request.getFile();
        if (file != null && !file.isEmpty()) {
            String original = file.getOriginalFilename();
            String safe = (original != null) ? original.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "file";
            storedFileName = UUID.randomUUID().toString() + "_" + safe;

            File dest = new File(uploadDir, storedFileName);
            file.transferTo(dest); // may throw IOException
        }

        // Create & save product
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .imageUrl(storedFileName) // store filename only (we will build full URL below)
                .build();
        Product saved = productRepository.save(product);

        // Build accessible image URL for frontend
        String imageUrl = null;
        if (storedFileName != null) {
            // Build URL like http://host:port/files/products/<filename>
            String base = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            // upload.folder is e.g. "uploads/products/" -> map to /files/products/<file>
            // we return URL starting with /files/...
            imageUrl = base + "/files/products/" + storedFileName;
        }

        return ProductDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .categoryId(category.getId())
                .imageUrl(imageUrl)
                .build();
    }

    @Override
    public ProductDto getById(UUID id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        String imageUrl = null;
        if (p.getImageUrl() != null) {
            String base = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            imageUrl = base + "/files/products/" + p.getImageUrl();
        }
        return ProductDto.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .imageUrl(imageUrl)
                .build();
    }
}
