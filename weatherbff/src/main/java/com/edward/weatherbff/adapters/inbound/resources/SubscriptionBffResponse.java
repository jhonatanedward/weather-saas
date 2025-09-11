package com.edward.weatherbff.adapters.inbound.resources;

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