package com.myapp.cruddemo.controller;

import com.myapp.cruddemo.dto.UserRequestDTO;
import com.myapp.cruddemo.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        return authService.register(userRequestDTO);
    }
}