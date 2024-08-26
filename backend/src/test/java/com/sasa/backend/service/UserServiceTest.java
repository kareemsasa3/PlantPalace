package com.sasa.backend.service;

import com.sasa.backend.dto.UserDTO;
import com.sasa.backend.entity.User;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.UserMapper;
import com.sasa.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // MockitoExtension handles initialization
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        UserDTO userDTO = UserMapper.toDTO(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO.getUsername(), result.getUsername());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
        assertEquals("User not found with id: 1", thrown.getMessage());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        UserDTO d1 = UserMapper.toDTO(user1);
        UserDTO d2 = UserMapper.toDTO(user2);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> userDTOs = userService.getAllUsers();

        assertNotNull(userDTOs);
        assertEquals(2, userDTOs.size());
        assertTrue(userDTOs.contains(d1));
        assertTrue(userDTOs.contains(d2));
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newuser");
        userDTO.setEmail("newuser@test.com");
        userDTO.setPassword("testpassword");
        User user = UserMapper.toEntity(userDTO);

        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getUsername(), result.getUsername());
    }

    @Test
    void testUpdateUser() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("updateduser");
        userDTO.setOrderHistory(new ArrayList<>());
        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setUsername("olduser");
        existingUser.setOrderHistory(new ArrayList<>());

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        UserDTO updatedUserDTO = userService.updateUser(id, userDTO);

        assertNotNull(updatedUserDTO);
        assertEquals(userDTO.getUsername(), updatedUserDTO.getUsername());
    }

    @Test
    void testDeleteUser() {
        Long id = 1L;
        User existingUser = new User();
        existingUser.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        doNothing().when(userRepository).delete(existingUser);

        userService.deleteUser(id);

        verify(userRepository, times(1)).delete(existingUser);
    }

}
