package com.edward.auth_service.controller;

import com.edward.auth_service.dto.AuthResponse;
import com.edward.auth_service.dto.SigninRequest;
import com.edward.auth_service.dto.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
@RequestMapping("/auth")
public interface AuthApi {

    @Operation(summary = "User registration",
            description = "Registers a new user with a username and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request or user already exists")
    })
    @PostMapping("/signup")
    ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request);

    @Operation(summary = "User login",
            description = "Authenticates an existing user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/signin")
    ResponseEntity<AuthResponse> signin(@RequestBody SigninRequest request);
}