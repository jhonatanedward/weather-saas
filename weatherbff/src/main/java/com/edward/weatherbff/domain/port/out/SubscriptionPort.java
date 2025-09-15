package com.edward.weatherbff.domain.port.out;

import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;

public interface SubscriptionPort {
    Subscription getSubscription(Long clientId);
    SubscriptionCreated createSubscription(Long userId, String email);
}
