package com.sasa.backend.dto.user;

import com.sasa.backend.entity.user.Role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private Long id;
    private RoleName roleName;  // Use existing enum
    private List<UserDTO> users;  // Use UserDTO if needed
}