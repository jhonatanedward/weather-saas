package com.edward.weather_subscription_service.infrastructure.adapter.controller;

import com.edward.weather_subscription_service.application.ports.in.SubscriptionRequest;
import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.advice.ApiResponse;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.PaymentProcessed;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.SubscriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Subscriptions", description = "Endpoints for managing user subscriptions and payments.")
@RequestMapping("/v1/subscriptions")
public interface SubscriptionApi {

    @PostMapping
    @Operation(summary = "Create a new subscription", description = "Creates a new subscription and returns a checkout URL.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Subscription created successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload.",
                    content = @Content)
    })
    ResponseEntity<ApiResponse<?>> createSubscription(@RequestBody SubscriptionRequest request);

    @GetMapping
    @Operation(summary = "Get subscription details", description = "Retrieves a user's subscription information by their ID.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Subscription found and returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Subscription not found for the given user ID.",
                    content = @Content)
    })
    ResponseEntity<ApiResponse<?>> getSubscription(
            @Parameter(description = "ID of the user to search for.") @RequestParam Long userId);

    @PostMapping("/payment-received")
    @Operation(summary = "Process a received payment", description = "Receives a webhook notification and processes the payment.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Payment processed successfully."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid payment payload.",
                    content = @Content)
    })
    ResponseEntity<ApiResponse<?>> receivePayment(@RequestBody PaymentProcessed paymentProcessed);
}