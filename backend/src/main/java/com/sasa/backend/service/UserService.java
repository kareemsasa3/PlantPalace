package com.sasa.backend.service;

import java.util.List;

import com.sasa.backend.dto.auth.UserRegistrationDTO;
import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.user.User;

public interface UserService {

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserRegistrationDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User findById(Long userId);
}
