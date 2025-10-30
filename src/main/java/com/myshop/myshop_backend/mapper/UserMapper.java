package com.myshop.myshop_backend.mapper;

import com.myshop.myshop_backend.dto.request.UserRequest;
import com.myshop.myshop_backend.dto.response.UserResponse;
import com.myshop.myshop_backend.entity.Role;
import com.myshop.myshop_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    // DTO → Entity
    User toEntity(UserRequest request);

    // Entity → DTO (javob uchun)
    UserResponse toResponse(User user);

    // Mavjud user’ni yangilash uchun
    void updateUserFromRequest(UserRequest request, @MappingTarget User user);

    // Set<String> ni Set<Role> ga o‘zgartirish uchun yordamchi metod
    default Set<Role> map(Set<String> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toSet());
    }

    // Agar kerak bo‘lsa, aksincha Set<Role> ni Set<String> ga o‘zgartirish metodi
    default Set<String> mapRolesToStrings(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
