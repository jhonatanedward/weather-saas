package com.edward.weather_subscription_service.infrastructure.adapter.gateway;

import com.edward.weather_subscription_service.application.ports.out.PaymentGatewayPort;
import com.edward.weather_subscription_service.application.ports.out.PaymentSession;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MockPaymentGatewayAdapter implements PaymentGatewayPort {

    @Override
    public PaymentSession createSubscriptionSession(String email, String planType) {
        System.out.println("Mocking payment gateway API call for user: with email: " + email);

        long randomNumber = Math.abs(new Random().nextLong());

        String mockExternalId = "sub_" + randomNumber + "_" + planType;

        String mockCheckoutUrl = "https://mock-checkout.com/session?id=" + mockExternalId;

        return new PaymentSession(mockCheckoutUrl, mockExternalId);
    }
}