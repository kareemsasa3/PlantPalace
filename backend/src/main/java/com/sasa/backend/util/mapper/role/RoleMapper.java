package com.sasa.backend.util.mapper.role;

import com.sasa.backend.dto.role.RoleDTO;
import com.sasa.backend.entity.role.Role;
import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    private RoleMapper() {
        throw new IllegalStateException();
    }

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDTO(role);
    }

    public static Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        return new Role(roleDTO);
    }

    public static List<RoleDTO> toDTOList(List<Role> roles) {
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                    .map(RoleMapper::toDTO)
                    .collect(Collectors.toList());
    }

    public static List<Role> toEntityList(List<RoleDTO> roleDTOs) {
        if (roleDTOs == null) {
            return Collections.emptyList();
        }
        return roleDTOs.stream()
                       .map(RoleMapper::toEntity)
                       .collect(Collectors.toList());
    }
}