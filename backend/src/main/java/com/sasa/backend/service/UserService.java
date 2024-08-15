package com.sasa.backend.service;

import java.util.List;

import com.sasa.backend.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
