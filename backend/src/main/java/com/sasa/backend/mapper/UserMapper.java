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
            .addresses(user.getAddresses() != null
                ? user.getAddresses().stream()
                    .map(AddressMapper::toDTO)
                    .collect(Collectors.toList())
                : new ArrayList<>())  // Added mapping for addresses
            .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
            .id(userDTO.getId())
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .firstName(userDTO.getFirstName())
            .lastName(userDTO.getLastName())
            .orderHistory(userDTO.getOrderHistory() != null
                ? userDTO.getOrderHistory().stream()
                    .map(OrderMapper::toEntity)
                    .collect(Collectors.toList())
                : new ArrayList<>())
            .roles(userDTO.getRoles() != null
                ? userDTO.getRoles().stream()
                    .map(UserMapper::roleToEntity)
                    .collect(Collectors.toList())
                : new ArrayList<>())
            .addresses(userDTO.getAddresses() != null
                ? userDTO.getAddresses().stream()
                    .map(AddressMapper::toEntity)
                    .collect(Collectors.toList())
                : new ArrayList<>())  // Added mapping for addresses
            .build();
    }

    public static RoleDTO roleToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    public static Role roleToEntity(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .build();
    }
}
