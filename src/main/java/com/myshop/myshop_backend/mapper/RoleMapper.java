package com.myshop.myshop_backend.mapper;

import com.myshop.myshop_backend.dto.request.RoleRequest;
import com.myshop.myshop_backend.dto.response.RoleResponse;
import com.myshop.myshop_backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toResponse(Role role);
    Role toEntity(RoleRequest request);
    void updateRoleFromRequest(RoleRequest request, @MappingTarget Role role);
}
