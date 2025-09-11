package com.edward.weather_subscription_service.infrastructure.adapter.controller;

import com.edward.weather_subscription_service.application.ports.in.CreateSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.FindSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.SubscriptionRequest;
import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.advice.ApiResponse;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.SubscriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final FindSubscriptionUseCase findSubscriptionUseCase;

    public SubscriptionController(CreateSubscriptionUseCase createSubscriptionUseCase, FindSubscriptionUseCase findSubscriptionUseCase) {
        this.createSubscriptionUseCase = createSubscriptionUseCase;
        this.findSubscriptionUseCase = findSubscriptionUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createSubscription(@RequestBody SubscriptionRequest request) {
        Subscription subscription = createSubscriptionUseCase.createSubscription(request);
        SubscriptionResponse response = new SubscriptionResponse(
                subscription.getCheckoutUrl(),
                subscription.getId().toString()
        );

        ApiResponse<SubscriptionResponse> apiResponse = ApiResponse.success(
                "Subscription created successfully.",
                response
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getSubscription(@RequestParam String userId) {
        Subscription subscription = findSubscriptionUseCase.findByUserId(userId);
        ApiResponse<Subscription> apiResponse = ApiResponse.success(
                "Ok",
                subscription
        );
        return ResponseEntity.ok(apiResponse);
    }
}
