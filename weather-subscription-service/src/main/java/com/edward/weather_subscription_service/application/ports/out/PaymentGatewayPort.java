package com.edward.weather_subscription_service.application.ports.out;

public interface PaymentGatewayPort {
    PaymentSession createSubscriptionSession(String email);
}
