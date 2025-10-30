package com.myshop.myshop_backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🛍️ MyShop API Documentation")
                        .description("""
                                Bu loyiha Spring Boot asosida yozilgan.
                                Backend qismi Product, Category, User va Role boshqaruvini o‘z ichiga oladi.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("MyShop Team")
                                .email("support@myshop.com")
                                .url("https://github.com/myshop"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Qo‘shimcha hujjatlar")
                        .url("https://github.com/myshop"));
    }
}
