package com.sasa.backend.util.mapper.auth;

import com.sasa.backend.dto.auth.UserRegistrationDTO;
import com.sasa.backend.entity.user.User;

public class UserRegistrationMapper {

    private UserRegistrationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User toUser(UserRegistrationDTO registrationDTO) {
        if (registrationDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setEmail(registrationDTO.getEmail());
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        return user;
    }
}