package com.example.DB_Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg/signup")
    public String signup() {
        return "signup";
    }
}
