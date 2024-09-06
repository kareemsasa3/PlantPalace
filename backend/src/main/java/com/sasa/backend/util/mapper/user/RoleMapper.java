package com.sasa.backend.util.mapper.user;

import com.sasa.backend.dto.user.RoleDTO;
import com.sasa.backend.entity.user.Role;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.dto.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    private RoleMapper() {
        throw new IllegalStateException("Util class");
    }

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        List<UserDTO> userDTOs = role.getUsers().stream()
                .map(UserMapper::toDTO) // Assuming UserMapper exists
                .collect(Collectors.toList());

        return new RoleDTO(
            role.getId(),
            role.getRoleName(),
            userDTOs
        );
    }

    public static Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        List<User> users = dto.getUsers().stream()
                .map(UserMapper::toEntity) // Assuming UserMapper exists
                .collect(Collectors.toList());

        return new Role(
            dto.getId(),
            dto.getRoleName(),
            users
        );
    }
}