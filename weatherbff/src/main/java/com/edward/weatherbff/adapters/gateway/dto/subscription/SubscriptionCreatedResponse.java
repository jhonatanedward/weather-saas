package com.edward.weatherbff.adapters.gateway.dto.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCreatedResponse {
    @JsonProperty("checkout_url")
    private String checkoutUrl;

    @JsonProperty("subscription_id")
    private String subscriptionId;
}
