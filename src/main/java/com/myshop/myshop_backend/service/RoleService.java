package com.myshop.myshop_backend.service;

import com.myshop.myshop_backend.dto.request.RoleRequest;
import com.myshop.myshop_backend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    List<RoleResponse> getAllRoles();
    RoleResponse getRoleById(Long id);
    RoleResponse updateRole(Long id, RoleRequest request);
    void deleteRole(Long id);
}
