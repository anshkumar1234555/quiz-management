package com.ansh.quiz_management.config;

import com.ansh.quiz_management.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // AUTH
                        // =========================
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // =========================
                        // QUIZ - ADMIN ONLY
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/quizzes/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/quizzes/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/quizzes/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // QUESTION - ADMIN ONLY
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/questions/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/questions/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/questions/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // OPTION - ADMIN ONLY
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/options/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/options/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/options/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/attempts"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/attempts/**"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/attempts/*/submit"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/attempts/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // EVERYTHING ELSE
                        // =========================
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}