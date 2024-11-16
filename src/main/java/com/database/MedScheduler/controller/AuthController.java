package com.database.MedScheduler.controller;

import com.database.MedScheduler.dto.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Endpoint pentru pagina de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returnează pagina login.html
    }

    // Endpoint pentru pagina de înregistrare
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        // Atașează un obiect RegistrationDto pentru formularul de înregistrare
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register"; // Returnează pagina register.html
    }
}
