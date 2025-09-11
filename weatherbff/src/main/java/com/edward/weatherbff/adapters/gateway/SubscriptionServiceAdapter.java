package com.edward.weatherbff.adapters.gateway;

import com.edward.weatherbff.adapters.controllers.resources.SubscriptionBffResponse;
import com.edward.weatherbff.adapters.gateway.dto.SubscriptionResponse;
import com.edward.weatherbff.adapters.gateway.dto.SubscriptionServiceRequest;
import com.edward.weatherbff.adapters.gateway.mappers.SubscriptionMapper;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionServiceAdapter implements SubscriptionPort {

    private final RestTemplate restTemplate;
    private final SubscriptionMapper subscriptionMapper;


    public SubscriptionServiceAdapter(RestTemplate restTemplate, SubscriptionMapper subscriptionMapper
    ) {
        this.subscriptionMapper = subscriptionMapper;
        this.restTemplate = restTemplate;
    }


    @Override
    public Subscription getSubscription(String clientId) {
        return null;
    }

    @Override
    @Retryable(
            value = { ResourceAccessException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public SubscriptionCreated createSubscription(Long userId, String email) {
        SubscriptionServiceRequest request = new SubscriptionServiceRequest(userId, email);

        String paymentServiceUrl = "http://localhost:8082/v1/subscriptions";

        ResponseEntity<SubscriptionResponse> response = restTemplate.postForEntity(
                paymentServiceUrl,
                request,
                SubscriptionResponse.class
        );

        return subscriptionMapper.toDomainModel(response.getBody().getData());
    }
}
