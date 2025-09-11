package com.edward.weatherbff.adapters.inbound;


import com.edward.weatherbff.adapters.inbound.utils.JwtUtil;
import com.edward.weatherbff.application.SubscriptionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;
    private final JwtUtil jwtUtil;

    public SubscriptionController(SubscriptionUseCase subscriptionUseCase, JwtUtil jwtUtil) {
        this.subscriptionUseCase = subscriptionUseCase;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping
    public ResponseEntity<?> subscribe(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String plan
            ) {
        var claims = jwtUtil.parseToken(authHeader);
        Long userId = claims.get("id", Long.class);
        String currentPlan = claims.get("plan", String.class);
        return ResponseEntity.ok(subscriptionUseCase.subscribeUser(userId, plan, currentPlan));
    }

}

