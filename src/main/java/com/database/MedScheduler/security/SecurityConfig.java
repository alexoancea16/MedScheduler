package com.database.MedScheduler.security;

import com.database.MedScheduler.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // Dezactivăm CSRF

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMINISTRATOR")
                .requestMatchers("/medic/**").hasRole("MEDIC")
                .requestMatchers("/pacient/**").hasRole("PACIENT")
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll() // Permitem acces public
                .anyRequest().authenticated() // Orice alt request necesită autentificare
        );

        http.formLogin(form -> form
                .loginPage("/login") // Pagină personalizată de login
                .defaultSuccessUrl("/home", true) // Redirect după autentificare cu succes
                .permitAll() // Permitem acces public la login
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redirect după logout
                .permitAll() // Permitem acces public la logout
        );

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
