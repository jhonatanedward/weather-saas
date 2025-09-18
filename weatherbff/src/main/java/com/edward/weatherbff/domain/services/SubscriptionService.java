package com.edward.weatherbff.domain.services;

import com.edward.weatherbff.adapters.controllers.resources.SubscriptionBffResponse;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;
import com.edward.weatherbff.domain.port.in.SubscriptionServicePort;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;

public class SubscriptionService implements SubscriptionServicePort {

    private final SubscriptionPort subscriptionPort;

    public SubscriptionService(SubscriptionPort subscriptionPort) {
        this.subscriptionPort = subscriptionPort;
    }
    @Override
    public SubscriptionCreated subscribeUser(Long userId, String email) {
        return subscriptionPort.createSubscription(userId, email);
    }

    @Override
    public Subscription getSubscription(Long userId) {
        return subscriptionPort.getSubscription(userId);
    }

}
