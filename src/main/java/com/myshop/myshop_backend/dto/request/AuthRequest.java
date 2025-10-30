package com.myshop.myshop_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @Email(message = "Email noto‘g‘ri formatda")
    @NotBlank(message = "Email bo‘sh bo‘lmasligi kerak")
    private String email;

    @NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak")
    private String password;
}
