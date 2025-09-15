package com.edward.weather_subscription_service.application.ports.out;

import com.edward.weather_subscription_service.application.model.Subscription;

public interface SubscriptionRepositoryPort {
    Subscription save(Subscription subscription);
    Subscription findByUserId(String userId);
    Subscription findByExternalSubscriptionId(String externalSubscriptionId);
}
