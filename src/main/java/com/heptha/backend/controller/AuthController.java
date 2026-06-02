package com.heptha.backend.controller;

import com.heptha.backend.dto.ApiResponse;
import com.heptha.backend.dto.AuthResponse;
import com.heptha.backend.dto.LoginRequest;
import com.heptha.backend.dto.RegisterRequest;
import com.heptha.backend.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);

        ApiResponse<AuthResponse> apiResponse =
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("User registered successfully")
                        .statusCode(201)
                        .data(response)
                        .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        ApiResponse<AuthResponse> apiResponse =
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .statusCode(200)
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}