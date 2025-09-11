package com.edward.weatherbff.adapters.outbound.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionServiceRequest(
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("email")
        String email) {
}
