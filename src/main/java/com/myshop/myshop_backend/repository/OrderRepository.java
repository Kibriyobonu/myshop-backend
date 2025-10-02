package com.myshop.myshop_backend.repository;
import com.myshop.myshop_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}