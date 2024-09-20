package com.sasa.backend.service.user;

import com.sasa.backend.dto.auth.UserRegistrationDTO;
import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.auth.UserDetailsImpl;
import com.sasa.backend.entity.role.Role;
import com.sasa.backend.entity.role.Role.RoleName;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.exception.DuplicateResourceException;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.repository.role.RoleRepository;
import com.sasa.backend.repository.user.UserRepository;
import com.sasa.backend.util.mapper.auth.UserRegistrationMapper;
import com.sasa.backend.util.mapper.user.UserMapper;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }    

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());

        User user = UserRegistrationMapper.toUser(userRegistrationDTO);
        user.setPassword(encodedPassword);

        Role role;
        if (userRegistrationDTO.getRoleName() != null) {
            try {
                RoleName roleName = RoleName.valueOf(userRegistrationDTO.getRoleName().toUpperCase());
                role = roleRepository.findByRoleName(roleName);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid role name provided: " + userRegistrationDTO.getRoleName());
            }
        } else {
            role = roleRepository.findByRoleName(RoleName.USER);
        }
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            
            // Map single role ID from UserDTO to Role entity
            if (userDTO.getRoleName() != null) {
                Optional<Role> role = Optional.of(roleRepository.findByRoleName(RoleName.valueOf(userDTO.getRoleName())));
                if (role.isPresent()) {
                    user.setRole(role.get());
                } else {
                    // Handle the case where the role name does not exist
                    throw new IllegalArgumentException("Role with ID " + userDTO.getRoleName() + " does not exist.");
                }
            }
            
            User updatedUser = userRepository.save(user);
            return UserMapper.toDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
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