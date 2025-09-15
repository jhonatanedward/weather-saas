package com.edward.weatherbff.adapters.controllers;


import com.edward.weatherbff.config.security.utils.JwtUtil;
import com.edward.weatherbff.domain.port.in.SubscriptionServicePort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionServicePort subscriptionServicePort;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> subscribe(
            @RequestHeader("Authorization") String authHeader,
            HttpServletRequest request
            ) {
        Long userId = (Long) request.getAttribute("userId");
        String currentPlan = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(subscriptionServicePort.subscribeUser(userId, currentPlan));
    }

}

