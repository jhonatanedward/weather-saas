package com.edward.weather_subscription_service.application.ports.in;


import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionRequest(
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("email")
        String email){

}