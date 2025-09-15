package com.edward.weather_subscription_service.application.services;

import com.edward.weather_subscription_service.domain.model.Plan;
import com.edward.weather_subscription_service.application.ports.in.ProcessPaymentUseCase;
import com.edward.weather_subscription_service.application.ports.out.MessageQueuePort;
import com.edward.weather_subscription_service.application.ports.out.SubscriptionRepositoryPort;
import com.edward.weather_subscription_service.application.services.exception.SubscriptionNotFoundException;
import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.controller.resources.PaymentProcessed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final SubscriptionRepositoryPort subscriptionRepositoryPort;
    private final MessageQueuePort messageQueuePort;
    private final ObjectMapper objectMapper;
    public ProcessPaymentService(
            SubscriptionRepositoryPort subscriptionRepositoryPort, MessageQueuePort messageQueuePort,
            ObjectMapper objectMapper) {
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
        this.messageQueuePort = messageQueuePort;
        this.objectMapper = objectMapper;
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
        subscription.setPlan(Plan.PREMIUM);
        subscriptionRepositoryPort.save(subscription);
        sendReceivedPaymentEvent(subscription);
    }

    private void sendReceivedPaymentEvent(Subscription subscription) {
        try {
            String sb = objectMapper.writeValueAsString(subscription);
            messageQueuePort.sendMessage(sb);
        }catch (JsonProcessingException ex) {
            // TODO
        }
    }
}
