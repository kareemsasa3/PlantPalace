package com.sasa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    
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
}
