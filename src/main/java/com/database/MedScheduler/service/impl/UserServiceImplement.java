package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.models.Role;
import com.database.MedScheduler.models.User;
import com.database.MedScheduler.repository.RoleRepository;
import com.database.MedScheduler.repository.UserRepository;
import com.database.MedScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class UserServiceImplement implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("User");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
}
