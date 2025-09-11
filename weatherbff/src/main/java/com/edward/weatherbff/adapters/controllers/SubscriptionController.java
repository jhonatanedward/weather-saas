package com.edward.weatherbff.adapters.controllers;


import com.edward.weatherbff.adapters.controllers.utils.JwtUtil;
import com.edward.weatherbff.domain.port.in.SubscriptionServicePort;
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
    public ResponseEntity<?> subscribe(@RequestHeader("Authorization") String authHeader) {
        var claims = jwtUtil.parseToken(authHeader);
        Long userId = claims.get("id", Long.class);
        String currentPlan = claims.get("email", String.class);
        return ResponseEntity.ok(subscriptionServicePort.subscribeUser(userId, currentPlan));
    }

}

