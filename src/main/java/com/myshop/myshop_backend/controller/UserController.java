package com.myshop.myshop_backend.controller;

import com.myshop.myshop_backend.dto.request.UserRequest;
import com.myshop.myshop_backend.dto.response.UserResponse;
import com.myshop.myshop_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ 1. Yangi user yaratish
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    // ✅ 2. Barcha userlarni olish
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ 3. ID bo‘yicha userni olish
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // ✅ 4. Userni yangilash
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    // ✅ 5. Userni o‘chirish
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
