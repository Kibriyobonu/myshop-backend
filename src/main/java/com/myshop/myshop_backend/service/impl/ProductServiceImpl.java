package com.myshop.myshop_backend.service.impl;

import com.myshop.myshop_backend.dto.request.ProductRequest;
import com.myshop.myshop_backend.dto.response.ProductResponse;
import com.myshop.myshop_backend.entity.Category;
import com.myshop.myshop_backend.entity.Product;
import com.myshop.myshop_backend.repository.CategoryRepository;
import com.myshop.myshop_backend.repository.ProductRepository;
import com.myshop.myshop_backend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static final String UPLOAD_DIR = "uploads/";

    // 🟢 Mahsulot yaratish
    @Override
    public ProductResponse createProduct(ProductRequest request, MultipartFile file) throws IOException {
        // 1️⃣ Kategoriya topish
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Kategoriya topilmadi"));

        // 2️⃣ Faylni saqlash
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.copy(file.getInputStream(), filePath);

            // Fayl uchun to‘liq URL yaratish
            imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
        }

        // 3️⃣ Entity yaratish
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(category)
                .imageUrl(imageUrl)
                .build();

        productRepository.save(product);

        // 4️⃣ Response qaytarish
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(category.getName())
                .imageUrl(imageUrl)
                .build();
    }

    // 🟢 Barcha mahsulotlarni olish
    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> ProductResponse.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .categoryName(p.getCategory().getName())
                        .imageUrl(p.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    // 🟢 ID orqali mahsulot olish
    @Override
    public ProductResponse getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mahsulot topilmadi"));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .imageUrl(product.getImageUrl())
                .build();
    }

    // 🟡 Mahsulotni yangilash
    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest request, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mahsulot topilmadi"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Kategoriya topilmadi"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.copy(file.getInputStream(), filePath);

            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

            product.setImageUrl(imageUrl);
        }

        productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(category.getName())
                .imageUrl(product.getImageUrl())
                .build();
    }

    // 🔴 Mahsulotni o‘chirish
    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
