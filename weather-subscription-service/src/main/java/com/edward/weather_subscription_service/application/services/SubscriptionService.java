package com.edward.weather_subscription_service.application.services;


import com.edward.weather_subscription_service.domain.model.Plan;
import com.edward.weather_subscription_service.application.ports.in.CreateSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.FindSubscriptionUseCase;
import com.edward.weather_subscription_service.application.ports.in.SubscriptionRequest;
import com.edward.weather_subscription_service.application.ports.out.PaymentGatewayPort;
import com.edward.weather_subscription_service.application.ports.out.PaymentSession;
import com.edward.weather_subscription_service.application.ports.out.SubscriptionRepositoryPort;
import com.edward.weather_subscription_service.application.services.exception.SubscriptionNotFoundException;
import com.edward.weather_subscription_service.application.services.exception.UserAlreadyHasSubscriptionException;
import com.edward.weather_subscription_service.domain.model.Subscription;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService implements CreateSubscriptionUseCase, FindSubscriptionUseCase {

    private final PaymentGatewayPort paymentGatewayPort;
    private final SubscriptionRepositoryPort subscriptionRepositoryPort;

    public SubscriptionService(PaymentGatewayPort paymentGatewayPort, SubscriptionRepositoryPort subscriptionRepositoryPort) {
        this.paymentGatewayPort = paymentGatewayPort;
        this.subscriptionRepositoryPort = subscriptionRepositoryPort;
    }

    @Override
    public Subscription createSubscription(SubscriptionRequest request) {

        Subscription subscription = subscriptionRepositoryPort.findByUserId(request.userId());

        if(subscription != null) {
            throw new UserAlreadyHasSubscriptionException("User aldready has a subscription");
        }

        PaymentSession session = paymentGatewayPort.createSubscriptionSession(request.email());

        Subscription newSubscription = new Subscription(
                request.userId(),
                session.getExternalSubscriptionId(),
                Plan.FREE,
                session.getCheckoutUrl()
        );

        return subscriptionRepositoryPort.save(newSubscription);
    }

    @Override
    public Subscription findByUserId(String userId) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepositoryPort.findByUserId(userId);
        if(subscription == null) {
            throw new SubscriptionNotFoundException("It's has no subscription for that user.");
        }
        return subscription;
    }
}