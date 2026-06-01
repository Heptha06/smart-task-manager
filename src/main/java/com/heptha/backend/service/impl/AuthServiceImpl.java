package com.heptha.backend.service.impl;

import com.heptha.backend.dto.AuthResponse;
import com.heptha.backend.dto.LoginRequest;
import com.heptha.backend.dto.RegisterRequest;
import com.heptha.backend.entity.User;
import com.heptha.backend.repository.UserRepository;
import com.heptha.backend.service.AuthService;
import com.heptha.backend.utils.JwtUtil;
import com.heptha.backend.exception.UserAlreadyExistsException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}