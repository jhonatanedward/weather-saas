package com.edward.weather_subscription_service.application.ports.in;

import com.edward.weather_subscription_service.application.model.Subscription;

public interface FindSubscriptionUseCase {
    Subscription findByUserId(String userId);
}
