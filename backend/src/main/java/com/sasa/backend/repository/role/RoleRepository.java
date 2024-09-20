package com.sasa.backend.repository.role;

import com.sasa.backend.entity.role.Role;
import com.sasa.backend.entity.role.Role.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Additional custom query methods can be defined here if needed
    Role findByRoleName(RoleName roleName);
}
