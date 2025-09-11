package com.edward.weather_subscription_service.infrastructure.adapter.controller.resources;

public class SubscriptionResponse {
    private String checkoutUrl;
    private String subscriptionId;

    public SubscriptionResponse(String checkoutUrl, String subscriptionId) {
        this.checkoutUrl = checkoutUrl;
        this.subscriptionId = subscriptionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}