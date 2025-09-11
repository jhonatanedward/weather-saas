package com.edward.weather_subscription_service.domain.model;

import java.util.UUID;

public class Subscription {
    private UUID id;
    private String userId;
    private String planType;
    private String externalSubscriptionId;
    private String checkoutUrl;
    private Status status;

    public Subscription(String userId, String externalSubscriptionId, String planType, String checkoutUrl) {
        this.userId = userId;
        this.externalSubscriptionId = externalSubscriptionId;
        this.checkoutUrl = checkoutUrl;
        this.status = Status.PENDING;
        this.planType = planType;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlanType() {
        return planType;
    }

    public String getExternalSubscriptionId() {
        return externalSubscriptionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }
    public String getStatus() {
        return this.status.name();
    }

    public boolean isFreePlan() {
        return "free".equalsIgnoreCase(planType);
    }
}