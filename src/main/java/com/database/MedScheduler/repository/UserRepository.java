package com.database.MedScheduler.repository;

import com.database.MedScheduler.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findFirstByEmail(String email);
}
