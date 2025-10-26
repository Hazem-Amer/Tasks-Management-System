package org.example.tasksmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.tasksmanagementsystem.dtos.auth.LoginRequestDto;
import org.example.tasksmanagementsystem.dtos.auth.LoginResponseDto;
import org.example.tasksmanagementsystem.dtos.auth.register.RegisterRequestDto;
import org.example.tasksmanagementsystem.model.ResponseModel;
import org.example.tasksmanagementsystem.service.AuthService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto req) {
        authService.register(req);
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("You have registered successfully")
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
        LoginResponseDto res = authService.login(req);
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("You have logged in successfully")
                        .data(Map.of("Token", res))
                        .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            authService.logout(token);
            return ResponseEntity.ok().body(
                    ResponseModel.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("You have logged out successfully")
                            .build());
        }
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("bearer token might be missing")
                        .build());

    }
}
