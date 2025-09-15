package com.edward.weather_subscription_service.application.services;

import com.edward.weather_subscription_service.application.ports.in.ProcessPaymentUseCase;
import com.edward.weather_subscription_service.application.ports.out.SubscriptionRepositoryPort;
import com.edward.weather_subscription_service.application.services.exception.SubscriptionNotFoundException;
import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.PaymentProcessed;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final SubscriptionRepositoryPort subscriptionRepositoryPort;
    public ProcessPaymentService(SubscriptionRepositoryPort subscriptionRepositoryPort) {
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
    }
    @Override
    public void proccessReceivedPayment(PaymentProcessed paymentProcessed) {
        Subscription subscription = subscriptionRepositoryPort.findByExternalSubscriptionId(paymentProcessed.externalSubscriptionId());
        if(subscription == null) {
            throw new SubscriptionNotFoundException(
                    String.format("Subscription with external reference id: %s not found.", paymentProcessed.externalSubscriptionId()));
        }
        subscription.setPaymentDate(paymentProcessed.paymentEffectedDate());
        subscription.activate();
        subscriptionRepositoryPort.save(subscription);
    }
}
