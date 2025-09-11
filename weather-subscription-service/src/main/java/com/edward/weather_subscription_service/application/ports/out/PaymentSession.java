package com.edward.weather_subscription_service.application.ports.out;

public class PaymentSession {
    private String checkoutUrl;
    private String externalSubscriptionId;

    public PaymentSession(String checkoutUrl, String externalSubscriptionId) {
        this.checkoutUrl = checkoutUrl;
        this.externalSubscriptionId = externalSubscriptionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public String getExternalSubscriptionId() {
        return externalSubscriptionId;
    }
}