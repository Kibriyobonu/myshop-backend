package com.myshop.myshop_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue
    private UUID id;  // ✅ UUID tipdagi id (agar xohlasang Long qilib o‘zgartirish mumkin)

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // ✅ Category ichida ko‘p Product bo‘lishi mumkin
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();
}
