package org.example.tasksmanagementsystem.service;



import org.example.tasksmanagementsystem.dtos.auth.LoginRequestDto;
import org.example.tasksmanagementsystem.dtos.auth.LoginResponseDto;
import org.example.tasksmanagementsystem.dtos.auth.register.RegisterRequestDto;
import org.example.tasksmanagementsystem.model.User;
import org.example.tasksmanagementsystem.repository.UserRepository;
import org.example.tasksmanagementsystem.security.JwtUtil;
import org.example.tasksmanagementsystem.security.TokenBlacklist;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TokenBlacklist tokenBlacklist;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil, TokenBlacklist tokenBlacklist) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklist = tokenBlacklist;
    }

    public void register(RegisterRequestDto req) {
        if (userRepository.existsByEmail(req.email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        String hashed = passwordEncoder.encode(req.password);
        User user = new User(req.name, req.email, hashed);
        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.email, req.password));
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(req.email);
        return new LoginResponseDto(token);
    }

    public void logout(String token) {
        tokenBlacklist.blacklistToken(token);
    }
}
