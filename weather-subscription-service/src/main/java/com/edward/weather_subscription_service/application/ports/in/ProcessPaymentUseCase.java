package com.edward.weather_subscription_service.application.ports.in;

import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.PaymentProcessed;

public interface ProcessPaymentUseCase {
    void proccessReceivedPayment(PaymentProcessed paymentProcessed);
}
