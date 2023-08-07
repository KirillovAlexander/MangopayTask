package com.verycoolapp.idea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/api/ideas").authenticated();
                    auth.requestMatchers(HttpMethod.POST, "/api/ideas").permitAll();
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                    auth.requestMatchers("/v3/api-docs/**").permitAll();
                    auth.anyRequest().denyAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults())
                .build();
    }
}
