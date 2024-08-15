package com.sasa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<OrderDTO> orderHistory;
}
