package com.myshop.myshop_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String password;

    // 👇 ManyToMany bog‘lanish
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // oraliq jadval
            joinColumns = @JoinColumn(name = "user_id"), // user_id ustuni
            inverseJoinColumns = @JoinColumn(name = "role_id") // role_id ustuni
    )
    private Set<Role> roles = new HashSet<>();
}
