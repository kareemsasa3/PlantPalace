package com.sasa.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.sasa.backend.dto.order.OrderDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<OrderDTO> orderHistory;  // Use OrderDTO
    private List<RoleDTO> roles;           // Use RoleDTO
    private List<AddressDTO> addresses;    // Use AddressDTO
}