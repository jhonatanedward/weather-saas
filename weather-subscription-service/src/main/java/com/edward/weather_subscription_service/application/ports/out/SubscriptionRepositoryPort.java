package com.edward.weather_subscription_service.application.ports.out;

import com.edward.weather_subscription_service.domain.model.Subscription;
import com.edward.weather_subscription_service.infrastructure.adapter.repository.SubscriptionEntity;

import java.util.Optional;

public interface SubscriptionRepositoryPort {
    Subscription save(Subscription subscription);
    Subscription findByUserId(String userId);
    Subscription findByExternalSubscriptionId(String externalSubscriptionId);
}
