package com.banking.online_banking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtAuthFilter jwtAuthFilter; // Add JwtAuthFilter


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for dev/testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/public-info", "/auth/**", "/css/**", "/js/**").permitAll() // public pages
                .anyRequest().authenticated() // everything else requires login
            )
            .formLogin(form -> form
                .loginPage("/auth/login") // your custom login page
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JwtAuthFilter to the chain

        return http.build();
    }
}