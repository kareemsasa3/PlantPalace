package com.sasa.backend.util.mapper.user;

import com.sasa.backend.dto.auth.UserRegistrationDTO;
import com.sasa.backend.entity.user.Role;
import com.sasa.backend.entity.user.User;

import java.util.Collections;

public class UserRegistrationMapper {

    private UserRegistrationMapper() {
        throw new IllegalStateException("Util class");
    }

    /**
     * Converts a UserRegistrationDTO to a User entity.
     *
     * @param registrationDTO the DTO containing user registration details
     * @return the User entity with initialized fields
     */
    public static User toEntity(UserRegistrationDTO registrationDTO) {
        return User.builder()
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(registrationDTO.getPassword()) // Ensure proper password hashing here
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .orderHistory(Collections.emptyList())  // Initialize with empty list
                .roles(Collections.singletonList(Role.builder()   // Initialize with default role
                        .roleName(Role.RoleName.USER)
                        .build()))
                .addresses(Collections.emptyList())  // Initialize with empty list
                .build();
    }
}