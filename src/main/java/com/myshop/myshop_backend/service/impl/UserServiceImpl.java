package com.myshop.myshop_backend.service.impl;

import com.myshop.myshop_backend.dto.request.UserRequest;
import com.myshop.myshop_backend.dto.response.UserResponse;
import com.myshop.myshop_backend.entity.Role;
import com.myshop.myshop_backend.entity.User;
import com.myshop.myshop_backend.mapper.UserMapper;
import com.myshop.myshop_backend.repository.RoleRepository;
import com.myshop.myshop_backend.repository.UserRepository;
import com.myshop.myshop_backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        // DTO -> Entity
        User user = userMapper.toEntity(request);

        // Rollarni aniqlash
        Set<Role> roles = new HashSet<>();
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            for (String roleName : request.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        } else {
            // Default rol: USER
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Default role 'USER' not found"));
            roles.add(defaultRole);
        }

        user.setRoles(roles);

        // Saqlash
        User savedUser = userRepository.save(user);

        // Response DTO
        UserResponse response = userMapper.toResponse(savedUser);
        response.setRoles(savedUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));

        return response;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse response = userMapper.toResponse(user);
                    response.setRoles(user.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toSet()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserResponse response = userMapper.toResponse(user);
        response.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));

        return response;
    }

    @Override
    public UserResponse updateUser(UUID id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // DTO’dan entity’ni yangilash
        userMapper.updateUserFromRequest(request, existingUser);

        // Rollarni yangilash (agar kiritilgan bo‘lsa)
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            Set<Role> updatedRoles = new HashSet<>();
            for (String roleName : request.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                updatedRoles.add(role);
            }
            existingUser.setRoles(updatedRoles);
        }

        User updatedUser = userRepository.save(existingUser);

        // Response DTO
        UserResponse response = userMapper.toResponse(updatedUser);
        response.setRoles(updatedUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));

        return response;
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
