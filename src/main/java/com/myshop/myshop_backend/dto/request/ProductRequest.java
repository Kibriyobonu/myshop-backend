package com.myshop.myshop_backend.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private UUID categoryId;

    // Rasmni jo‘natish uchun
    private MultipartFile file;
}
