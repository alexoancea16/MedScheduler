package com.example.DB_Project.Security;

import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, DefaultSslBundleRegistry sslBundleRegistry, DefaultSslBundleRegistry registry) throws Exception {
        return httpSecurity
                .formLogin(httpForm -> {
                    httpForm
                            .loginPage("/login").permitAll();
                })
                .authorizeRequests(registry -> {
                    registry.requestMatchers("/reg/sigup").permitAll();
                    registry.anyRequest().authenticated();
                })
                .build();
    }

}
