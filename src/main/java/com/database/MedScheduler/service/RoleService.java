package com.database.MedScheduler.service;

import com.database.MedScheduler.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByName(String name);

    Role saveRole(Role role);
}
