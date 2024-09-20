package com.sasa.backend.service.role;

import com.sasa.backend.dto.role.RoleDTO;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    Optional<RoleDTO> getRoleById(Long id);
    List<RoleDTO> getAllRoles();
    RoleDTO updateRole(Long id, RoleDTO roleDTO);
    void deleteRole(Long id);
}
