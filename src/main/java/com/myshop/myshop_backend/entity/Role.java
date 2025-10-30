package com.myshop.myshop_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // 👇 ManyToMany orqaga bog‘lanish (mappedBy = "roles")
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
