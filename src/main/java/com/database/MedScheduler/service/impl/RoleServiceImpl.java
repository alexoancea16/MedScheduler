package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.models.Role;
import com.database.MedScheduler.repository.RoleRepository;
import com.database.MedScheduler.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        // Corectarea erorii prin utilizarea Optional
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}

