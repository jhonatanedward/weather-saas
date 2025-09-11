package com.edward.weather_subscription_service.application.ports.in;

public class SubscriptionRequest {
    private String userId;
    private String email;
    private String planType;

    // Construtor, getters e setters
    public SubscriptionRequest(String userId, String email, String planType) {
        this.userId = userId;
        this.email = email;
        this.planType = planType;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPlanType() {
        return planType;
    }
}