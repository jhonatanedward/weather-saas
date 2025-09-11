package com.edward.auth_service.controller;

import com.edward.auth_service.dto.*;
import com.edward.auth_service.entity.User;
import com.edward.auth_service.service.AuthService;
import com.edward.auth_service.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        User user = authService.signup(request);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getUsername(), token));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SigninRequest request) {
        User user = authService.signin(request);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getUsername(), token));
    }
}
