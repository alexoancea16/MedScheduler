package com.database.MedScheduler.service;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.models.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
}
