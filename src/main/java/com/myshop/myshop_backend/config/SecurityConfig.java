package com.myshop.myshop_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔒 CSRF himoyasini vaqtincha o‘chirib qo‘yish (API test uchun)
                .csrf(csrf -> csrf.disable())

                // 🔓 Har bir endpoint uchun ruxsatni belgilash
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/uploads/**",
                                "/api/products/**",
                                "/api/categories/**"
                        ).permitAll()   // shu yo‘llarga login shart emas
                        .anyRequest().permitAll() // hozircha hammasiga ruxsat
                )

                // 🧩 Login sahifasini butunlay o‘chirib qo‘yamiz
                .httpBasic(Customizer.withDefaults()); // oddiy basic auth (Postman uchun qulay)

        return http.build();
    }
}
