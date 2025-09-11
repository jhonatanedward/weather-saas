package com.edward.weather_subscription_service.infrastructure.adapter.controller;

import com.edward.weather_subscription_service.application.ports.in.CreateSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.SubscriptionRequest;
import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.SubscriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;

    public SubscriptionController(CreateSubscriptionUseCase createSubscriptionUseCase) {
        this.createSubscriptionUseCase = createSubscriptionUseCase;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody SubscriptionRequest request) {
        try {
            Subscription subscription = createSubscriptionUseCase.createSubscription(request);
            SubscriptionResponse response = new SubscriptionResponse(
                    subscription.getCheckoutUrl(),
                    subscription.getId().toString()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
