package com.edward.weather_subscription_service.application.ports.out;

import com.edward.weather_subscription_service.domain.model.Subscription;

public interface PaymentGatewayPort {
    PaymentSession createSubscriptionSession(String email, String planType);
}
