package com.myshop.myshop_backend.repository;

import com.myshop.myshop_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
