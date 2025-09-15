package com.edward.weatherbff.adapters.gateway.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class SubscriptionData {

    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("external_subscription_id")
    private String externalSubscriptionId;

    @JsonProperty("payment_date")
    private LocalDateTime paymentDate;

    @JsonProperty("checkout_url")
    private String checkoutUrl;

    private String status;

    @JsonProperty("free_plan")
    private boolean freePlan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanType() {
        return plan;
    }

    public void setPlanType(String plan) {
        this.plan = plan;
    }

    public String getExternalSubscriptionId() {
        return externalSubscriptionId;
    }

    public void setExternalSubscriptionId(String externalSubscriptionId) {
        this.externalSubscriptionId = externalSubscriptionId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public void setCheckoutUrl(String checkoutUrl) {
        this.checkoutUrl = checkoutUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFreePlan() {
        return freePlan;
    }

    public void setFreePlan(boolean freePlan) {
        this.freePlan = freePlan;
    }
}