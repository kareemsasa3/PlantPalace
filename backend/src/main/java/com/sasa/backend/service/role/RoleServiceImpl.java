package com.sasa.backend.service.role;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.role.RoleDTO;
import com.sasa.backend.entity.role.Role;
import com.sasa.backend.entity.role.Role.RoleName;
import com.sasa.backend.repository.role.RoleRepository;
import com.sasa.backend.util.mapper.role.RoleMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        validateRoleName(roleDTO.getRoleName());
        Role role = RoleMapper.toEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.toDTO(savedRole);
    }

    @Override
    public Optional<RoleDTO> getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return Optional.of(RoleMapper.toDTO(role.get()));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::toDTO).toList();
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setRoleName(RoleName.valueOf(roleDTO.getRoleName()));
                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new IllegalArgumentException(Constants.ROLE_NOT_FOUND_MESSAGE + id));
        return RoleMapper.toDTO(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    private void validateRoleName(String roleName) {
        try {
            Role.RoleName.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }
    }
}