package com.sasa.backend.service;

import com.sasa.backend.dto.UserDTO;
import com.sasa.backend.entity.User;
import com.sasa.backend.entity.UserDetailsImpl;
import com.sasa.backend.exception.DuplicateResourceException;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.OrderMapper;
import com.sasa.backend.mapper.UserMapper;
import com.sasa.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }    

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        // Encrypt password
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Convert DTO to entity and set the encrypted password
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(encodedPassword);

        // Save user entity to the repository
        User savedUser = userRepository.save(user);

        // Convert saved entity to DTO and return
        return UserMapper.toDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setOrderHistory(userDTO.getOrderHistory().stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList()));
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setRoles(userDTO.getRoles().stream()
                .map(UserMapper::roleToEntity)
                .collect(Collectors.toList()));
            User updatedUser = userRepository.save(user);
            return UserMapper.toDTO(updatedUser);
        }
        return null; // Handle user not found case
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

}
