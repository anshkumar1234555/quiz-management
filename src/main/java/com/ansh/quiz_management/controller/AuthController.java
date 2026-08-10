package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.AuthResponse;
import com.ansh.quiz_management.dto.LoginRequest;
import com.ansh.quiz_management.dto.RegisterRequest;
import com.ansh.quiz_management.entity.User;
import com.ansh.quiz_management.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}