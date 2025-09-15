package com.edward.weatherbff.adapters.gateway;


import com.edward.weatherbff.adapters.gateway.dto.subscription.ApiResponse;
import com.edward.weatherbff.adapters.gateway.dto.subscription.SubscriptionCreatedResponse;
import com.edward.weatherbff.adapters.gateway.dto.subscription.SubscriptionData;
import com.edward.weatherbff.adapters.gateway.dto.subscription.SubscriptionServiceRequest;
import com.edward.weatherbff.adapters.gateway.mappers.SubscriptionMapper;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    ParameterizedTypeReference<ApiResponse<SubscriptionCreatedResponse>> typeReference =
            new ParameterizedTypeReference<>() {};


    public SubscriptionServiceAdapter(RestTemplate restTemplate, SubscriptionMapper subscriptionMapper
    ) {
        this.subscriptionMapper = subscriptionMapper;
        this.restTemplate = restTemplate;
    }


    @Override
    public Subscription getSubscription(Long clientId) {

        String subscriptionServiceUrl = "http://localhost:8082/v1/subscriptions?userId=" + clientId;

        ParameterizedTypeReference<ApiResponse<SubscriptionData>> typeReference =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<ApiResponse<SubscriptionData>> response = restTemplate.exchange(
                subscriptionServiceUrl,
                HttpMethod.GET,
                null,
                typeReference
        );


        return subscriptionMapper.toDomainModel(response.getBody().getData());
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

        ResponseEntity<ApiResponse<SubscriptionCreatedResponse>> response = restTemplate.exchange(
                paymentServiceUrl,
                HttpMethod.POST,
                new HttpEntity<>(request), // Wrap your request body in an HttpEntity
                typeReference
        );

        return subscriptionMapper.toCreatedDomainModel(response.getBody().getData());
    }
}
