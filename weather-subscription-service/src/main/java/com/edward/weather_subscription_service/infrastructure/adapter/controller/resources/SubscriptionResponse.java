package com.edward.weather_subscription_service.infrastructure.adapter.controller.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SubscriptionResponse {
    private String checkoutUrl;
    private String subscriptionId;

}