package com.myshop.myshop_backend.repository;

import com.myshop.myshop_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
