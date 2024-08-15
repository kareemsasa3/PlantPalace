package com.sasa.backend.mapper;

import com.sasa.backend.dto.RoleDTO;
import com.sasa.backend.dto.UserDTO;
import com.sasa.backend.entity.Role;
import com.sasa.backend.entity.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class UserMapper {

    private UserMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null; // Handle null users gracefully
        }

        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .orderHistory(user.getOrderHistory() != null
                ? user.getOrderHistory().stream()
                    .map(OrderMapper::toDTO)
                    .collect(Collectors.toList())
                : new ArrayList<>())
            .roles(user.getRoles() != null
                ? user.getRoles().stream()
                    .map(UserMapper::roleToDTO)
                    .collect(Collectors.toList())
                : new ArrayList<>())
            .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null; // Handle null DTOs gracefully
        }

        User user = User.builder()
            .id(userDTO.getId())
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .firstName(userDTO.getFirstName())
            .lastName(userDTO.getLastName())
            .orderHistory(new ArrayList<>())
            .roles(userDTO.getRoles() != null
                ? userDTO.getRoles().stream()
                    .map(UserMapper::roleToEntity)
                    .collect(Collectors.toList())
                : new ArrayList<>())
            .build();

        if (userDTO.getOrderHistory() != null) {
            user.setOrderHistory(userDTO.getOrderHistory().stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList()));
        }

        return user;
    }

    private static RoleDTO roleToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    private static Role roleToEntity(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .build();
    }
}
