package com.myshop.myshop_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak")
    private String firstName;

    @NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak")
    private String lastName;

    @Email(message = "Email noto‘g‘ri formatda")
    @NotBlank(message = "Email bo‘sh bo‘lmasligi kerak")
    private String email;

    @NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak")
    private String password;

    private String phoneNumber;
}
