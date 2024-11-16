package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.models.User;
import com.database.MedScheduler.repository.UserRepository;
import com.database.MedScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Returnăm direct Optional<User>
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username); // Metoda din repo returnează un Optional
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email); // Metoda din repo returnează un Optional
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptează parola
        return userRepository.save(user);
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); // Criptează parola
        user.setEnabled(true); // Setați valorile implicite
        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

