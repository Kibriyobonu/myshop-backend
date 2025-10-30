package com.myshop.myshop_backend.service.impl;

import com.myshop.myshop_backend.dto.request.RoleRequest;
import com.myshop.myshop_backend.dto.response.RoleResponse;
import com.myshop.myshop_backend.entity.Role;
import com.myshop.myshop_backend.mapper.RoleMapper;
import com.myshop.myshop_backend.repository.RoleRepository;
import com.myshop.myshop_backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toEntity(request);
        Role saved = roleRepository.save(role);
        return roleMapper.toResponse(saved);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleMapper.updateRoleFromRequest(request, existing);
        Role updated = roleRepository.save(existing);
        return roleMapper.toResponse(updated);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
