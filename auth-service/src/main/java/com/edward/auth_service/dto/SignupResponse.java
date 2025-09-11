package com.edward.auth_service.dto;

public record SignupResponse(Long id, String username, String role, String token) {}