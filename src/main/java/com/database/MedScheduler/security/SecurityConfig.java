package com.database.MedScheduler.security;

import com.database.MedScheduler.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Dezactivăm CSRF pentru simplitate. Activează în producție cu configurare corespunzătoare.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll() // Permitem accesul public
                        .requestMatchers("/admin/**").hasRole("ADMINISTRATOR") // Acces doar pentru ADMINISTRATOR
                        .requestMatchers("/medic/**").hasRole("MEDIC") // Acces doar pentru MEDIC
                        .requestMatchers("/pacient/**").hasRole("PACIENT") // Acces doar pentru PACIENT
                        .anyRequest().authenticated() // Orice alt request necesită autentificare
                )
                .formLogin(form -> form
                        .loginPage("/login") // Pagină personalizată de login
                        .defaultSuccessUrl("/home", true) // Redirecționare după autentificare cu succes
                        .permitAll() // Permitem acces la pagina de login
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Endpoint pentru logout
                        .logoutSuccessUrl("/login?logout") // Redirecționare după logout
                        .invalidateHttpSession(true) // Invalidează sesiunea
                        .deleteCookies("JSESSIONID") // Șterge cookie-urile
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder pentru parole
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
