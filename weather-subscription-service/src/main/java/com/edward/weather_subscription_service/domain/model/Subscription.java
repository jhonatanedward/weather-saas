package com.edward.weather_subscription_service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private UUID id;
    private String userId;
    private String planType;
    private String externalSubscriptionId;
    private LocalDateTime paymentDate;
    private String checkoutUrl;
    private Status status;

    public Subscription(String userId, String externalSubscriptionId, String planType, String checkoutUrl) {
        this.userId = userId;
        this.externalSubscriptionId = externalSubscriptionId;
        this.planType = planType;
        this.checkoutUrl = checkoutUrl;
        this.status = Status.PENDING;
    }

    public Subscription() {

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
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
    public void activate() {
        this.status = Status.ACTIVE;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isFreePlan() {
        return "free".equalsIgnoreCase(planType);
    }
}