package com.myshop.myshop_backend.repository;

import com.myshop.myshop_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

