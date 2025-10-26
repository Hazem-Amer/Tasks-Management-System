package org.example.tasksmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.tasksmanagementsystem.dtos.auth.LoginRequestDto;
import org.example.tasksmanagementsystem.dtos.auth.LoginResponseDto;
import org.example.tasksmanagementsystem.dtos.auth.register.RegisterRequestDto;
import org.example.tasksmanagementsystem.service.AuthService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto req) {
        authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
        LoginResponseDto res = authService.login(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            authService.logout(token);
        }
        return ResponseEntity.ok().build();
    }
}
