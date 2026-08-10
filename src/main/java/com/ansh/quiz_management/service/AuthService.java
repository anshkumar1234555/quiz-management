package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.AuthResponse;
import com.ansh.quiz_management.dto.LoginRequest;
import com.ansh.quiz_management.dto.RegisterRequest;
import com.ansh.quiz_management.entity.User;

public interface AuthService {

    User register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}