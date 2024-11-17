package com.database.MedScheduler.service.impl;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.models.FisaMedicala;
import com.database.MedScheduler.models.Pacient;
import com.database.MedScheduler.models.User;
import com.database.MedScheduler.repository.FisaMedicalaRepository;
import com.database.MedScheduler.repository.PacientRepository;
import com.database.MedScheduler.repository.UserRepository;
import com.database.MedScheduler.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PacientRepository pacientRepository;
    private final FisaMedicalaRepository fisaMedicalaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PacientRepository pacientRepository,
                           FisaMedicalaRepository fisaMedicalaRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.pacientRepository = pacientRepository;
        this.fisaMedicalaRepository = fisaMedicalaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        // Crearea utilizatorului
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        // Crearea pacientului
        Pacient pacient = new Pacient();
        pacient.setNume(registrationDto.getNume());
        pacient.setPrenume(registrationDto.getPrenume());
        pacient.setCnp(registrationDto.getCnp());
        pacient.setDataNasterii(LocalDate.parse(registrationDto.getDataNasterii())); // Conversie String -> LocalDate
        pacient.setSex(registrationDto.getSex());
        pacient.setTelefon(registrationDto.getTelefon());
        pacient.setAdresa(registrationDto.getAdresa());
        pacientRepository.save(pacient);

        // Crearea fișei medicale
        FisaMedicala fisaMedicala = new FisaMedicala();
        fisaMedicala.setPacient(pacient); // Asociem fișa medicală cu pacientul
        fisaMedicala.setAlergii(registrationDto.getAlergii());
        fisaMedicala.setGrupaSange(registrationDto.getGrupaSange());
        fisaMedicala.setGreutate(registrationDto.getGreutate());
        fisaMedicala.setInaltime(registrationDto.getInaltime());
        fisaMedicalaRepository.save(fisaMedicala);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
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
