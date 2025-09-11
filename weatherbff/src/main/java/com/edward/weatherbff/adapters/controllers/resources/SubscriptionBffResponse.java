package com.edward.weatherbff.adapters.controllers.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionBffResponse {
    private String checkoutUrl;
    private String subscriptionId;
}