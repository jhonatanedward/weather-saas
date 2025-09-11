package com.edward.weather_subscription_service.application.ports.in;

import com.edward.weather_subscription_service.domain.model.Subscription;

public interface CreateSubscriptionUseCase {
    Subscription createSubscription(SubscriptionRequest request);
}