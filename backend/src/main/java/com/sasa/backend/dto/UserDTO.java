package com.sasa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {

    public UserDTO() {
        orderHistory = new ArrayList<>();
        roles = new ArrayList<>();
        addresses = new ArrayList<>();
    }
    
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;

    private String firstName;
    private String lastName;
    
    private List<OrderDTO> orderHistory;
    private List<RoleDTO> roles;
    private List<AddressDTO> addresses;  // Added field for addresses
}
