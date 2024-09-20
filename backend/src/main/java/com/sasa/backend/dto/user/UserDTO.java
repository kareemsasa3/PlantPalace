package com.sasa.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.sasa.backend.entity.user.User;

import io.jsonwebtoken.lang.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    public UserDTO(Long userId) {
        this.id = userId;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.orderHistoryIds = Collections.emptyList();
        this.addressIds = Collections.emptyList();
        this.roleName = user.getRole() != null ? user.getRole().getRoleName().name() : null;
    }

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private List<Long> orderHistoryIds;
    
    private List<Long> addressIds;

    private String roleName;
}