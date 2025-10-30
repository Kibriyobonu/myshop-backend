package com.myshop.myshop_backend.config;

import com.myshop.myshop_backend.entity.Role;
import com.myshop.myshop_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(Role.builder()
                    .name("USER")
                    .build());
        }
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(Role.builder()
                    .name("ADMIN")
                    .build());
        }
    }
}
