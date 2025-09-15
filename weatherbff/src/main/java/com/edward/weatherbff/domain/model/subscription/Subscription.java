package com.edward.weatherbff.domain.model.subscription;

import java.time.Instant;

public class Subscription {
    private Long userId;
    private String email;
    private Plan plan;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Plan getPlan() {
        return plan;
    }
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setPlanType(Plan planType) {
        this.plan = plan;
    }
}
