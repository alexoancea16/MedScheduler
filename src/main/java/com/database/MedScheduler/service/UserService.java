package com.database.MedScheduler.service;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    User saveUser(User user);

    void saveUser(RegistrationDto registrationDto); // Noua metodă

    boolean deleteUser(Long id);
}
