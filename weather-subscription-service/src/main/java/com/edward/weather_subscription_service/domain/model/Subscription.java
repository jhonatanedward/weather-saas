package com.edward.weather_subscription_service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private UUID id;
    private String userId;
    private Plan plan;
    private String externalSubscriptionId;
    private LocalDateTime paymentDate;
    private String checkoutUrl;
    private Status status;

    public Subscription(String userId, String externalSubscriptionId, Plan plan, String checkoutUrl) {
        this.userId = userId;
        this.externalSubscriptionId = externalSubscriptionId;
        this.plan = plan;
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

    public Plan getPlan() {
        return plan;
    }
    public void setPlan(Plan plan) {
        this.plan = plan;
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

}