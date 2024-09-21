package com.sasa.backend.entity.role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.sasa.backend.dto.role.RoleDTO;
import com.sasa.backend.entity.user.User;

import io.jsonwebtoken.lang.Collections;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public enum RoleName {
        ADMIN,
        GUEST,
        MODERATOR,
        USER;
    }

    public Role(RoleDTO dto) {
        this.id = dto.getId();
        this.roleName = RoleName.valueOf(dto.getRoleName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users = Collections.emptyList();
}
