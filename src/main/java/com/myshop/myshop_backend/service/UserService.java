package com.myshop.myshop_backend.service;

import com.myshop.myshop_backend.dto.request.UserRequest;
import com.myshop.myshop_backend.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    UserResponse updateUser(UUID id, UserRequest request);
    void deleteUser(UUID id);
}
