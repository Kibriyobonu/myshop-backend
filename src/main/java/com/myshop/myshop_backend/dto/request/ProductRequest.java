package com.myshop.myshop_backend.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Double price;
}