package com.myshop.myshop_backend.repository;
import com.myshop.myshop_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
