package com.edward.weather_subscription_service.application.services;


import com.edward.weather_subscription_service.application.ports.in.CreateSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.SubscriptionRequest;
import com.edward.weather_subscription_service.application.ports.out.PaymentGatewayPort;
import com.edward.weather_subscription_service.application.ports.out.PaymentSession;
import com.edward.weather_subscription_service.application.ports.out.SubscriptionRepositoryPort;
import com.edward.weather_subscription_service.domain.model.Subscription;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService implements CreateSubscriptionUseCase {

    private final PaymentGatewayPort paymentGatewayPort;
    private final SubscriptionRepositoryPort subscriptionRepositoryPort;

    public SubscriptionService(PaymentGatewayPort paymentGatewayPort, SubscriptionRepositoryPort subscriptionRepositoryPort) {
        this.paymentGatewayPort = paymentGatewayPort;
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
    }

    @Override
    public Subscription createSubscription(SubscriptionRequest request) {
        if (request.getPlanType() == null
                        || (!request.getPlanType().equalsIgnoreCase("free")
                            && !request.getPlanType().equalsIgnoreCase("premium"))) {
            throw new IllegalArgumentException("Invalid plan type: " + request.getPlanType());
        }

        PaymentSession session = paymentGatewayPort.createSubscriptionSession(request.getEmail(), request.getPlanType());

        Subscription newSubscription = new Subscription(
                request.getUserId(),
                request.getPlanType(),
                session.getExternalSubscriptionId(),
                session.getCheckoutUrl()
        );

        return subscriptionRepositoryPort.save(newSubscription);
    }
}