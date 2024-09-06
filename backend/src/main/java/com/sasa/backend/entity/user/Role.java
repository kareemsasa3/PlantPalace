package com.sasa.backend.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    public enum RoleName {
        ADMIN,
        USER,
        MODERATOR,
        // Add other roles as needed
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private RoleName roleName;  // e.g., ROLE_USER, ROLE_ADMIN

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
