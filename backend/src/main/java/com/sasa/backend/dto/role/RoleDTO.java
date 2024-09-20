package com.sasa.backend.dto.role;

import java.util.List;
import java.util.stream.Collectors;

import com.sasa.backend.entity.role.Role;
import com.sasa.backend.entity.user.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName().name();
        this.usernames = role.getUsers() != null
            ? role.getUsers().stream().map(User::getUsername).collect(Collectors.toList())
            : null;
    }

    private Long id;

    @NotNull(message = "Role name must not be null")
    @Pattern(regexp = "ADMIN|USER|MODERATOR|GUEST", message = "Invalid role name")
    private String roleName;

    private List<String> usernames;
}