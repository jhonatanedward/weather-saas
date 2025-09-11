package com.edward.weatherbff.application.port;

import com.edward.weatherbff.adapters.inbound.resources.SubscriptionBffResponse;

public interface SubscriptionServicePort {
    SubscriptionBffResponse subscribe(Long userId, String email);
}