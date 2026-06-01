package com.heptha.backend.controller;

import com.heptha.backend.dto.AuthResponse;
import com.heptha.backend.dto.LoginRequest;
import com.heptha.backend.dto.RegisterRequest;
import com.heptha.backend.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }
}