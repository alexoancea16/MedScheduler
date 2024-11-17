package com.database.MedScheduler.controller;

import com.database.MedScheduler.dto.RegistrationDto;
import com.database.MedScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint pentru pagina de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returnează pagina login.html
    }

    // Endpoint pentru pagina de înregistrare (GET - afișare formular)
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        // Creează un obiect RegistrationDto pentru a popula formularul de înregistrare
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register"; // Thymeleaf va căuta register.html
    }

    // Endpoint pentru înregistrarea utilizatorului (POST - procesare formular)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationDto registrationDto, Model model) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            model.addAttribute("error", "Parola și confirmarea parolei nu coincid!");
            return "register"; // Revine la pagina de înregistrare
        }
        userService.saveUser(registrationDto);
        return "redirect:/login?success";
    }

    // Endpoint pentru pagina principală (home)
    @GetMapping("/home")
    public String home() {
        return "home"; // Thymeleaf va căuta home.html
    }
    @GetMapping("/")
    public String index() {
        return "index"; // Returnează pagina index.html
    }
}
