package com.edward.weatherbff.adapters.controllers.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record SubscriptionBffResponse(

        @JsonProperty("id")
        String id,
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("plan")
        String plan,
        @JsonProperty("external_subscription_id")
        String externalSubscriptionId,
        @JsonProperty("payment_date")
        String paymentDate,
        @JsonProperty("status")
        String status
) {
}