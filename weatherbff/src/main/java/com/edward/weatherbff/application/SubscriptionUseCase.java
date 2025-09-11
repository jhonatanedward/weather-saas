package com.edward.weatherbff.application;

import com.edward.weatherbff.adapters.inbound.resources.SubscriptionBffResponse;
import com.edward.weatherbff.application.port.SubscriptionServicePort;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionUseCase {

    private final SubscriptionServicePort subscriptionServicePort;

    public SubscriptionUseCase(SubscriptionServicePort subscriptionServicePort) {
        this.subscriptionServicePort = subscriptionServicePort;
    }

    public SubscriptionBffResponse subscribeUser(Long userId, String email, String plan) {
        return subscriptionServicePort.subscribe(userId, email, plan);
    }
}
