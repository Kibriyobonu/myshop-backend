package com.myshop.myshop_backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;  // frontendga qaytadi
    private UUID categoryId;
}
