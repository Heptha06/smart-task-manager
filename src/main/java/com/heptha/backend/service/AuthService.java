package com.heptha.backend.service;

import com.heptha.backend.dto.AuthResponse;
import com.heptha.backend.dto.LoginRequest;
import com.heptha.backend.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}