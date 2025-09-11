package com.edward.weatherbff.domain.model.subscription;

public class SubscriptionCreated {
    private String checkoutUrl;
    private String subscriptionId;

    public SubscriptionCreated(String checkoutUrl, String subscriptionId) {
        this.checkoutUrl = checkoutUrl;
        this.subscriptionId = subscriptionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public void setCheckoutUrl(String checkoutUrl) {
        this.checkoutUrl = checkoutUrl;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
