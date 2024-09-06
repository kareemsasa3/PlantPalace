package com.sasa.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sasa.backend.dto.auth.LoginResponseDTO;
import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.repository.UserRepository;
import com.sasa.backend.util.JwtUtil;
import com.sasa.backend.util.mapper.user.UserMapper;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public LoginResponseDTO login(String username, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername()); // Assuming `generateToken` method accepts a username

        // Convert User to UserDTO using the UserMapper
        UserDTO userDTO = UserMapper.toDTO(user);

        return new LoginResponseDTO(token, userDTO);
    }

}
