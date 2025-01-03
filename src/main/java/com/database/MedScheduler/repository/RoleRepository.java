package com.database.MedScheduler.repository;

import com.database.MedScheduler.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional <Role> findByName(String name);
}
