package com.edward.weatherbff.domain.port.in;

import com.edward.weatherbff.adapters.controllers.resources.SubscriptionBffResponse;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;

public interface SubscriptionServicePort {
    SubscriptionCreated subscribeUser(Long userId, String email);
    Subscription getSubscription(Long userId);
}
