package com.edward.weatherbff.adapters.outbound;

import com.edward.weatherbff.adapters.inbound.resources.SubscriptionBffResponse;
import com.edward.weatherbff.adapters.outbound.dto.SubscriptionServiceRequest;
import com.edward.weatherbff.application.port.SubscriptionServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionServiceAdapter implements SubscriptionServicePort {

    private final RestTemplate restTemplate;

    public SubscriptionServiceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(
            value = { ResourceAccessException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public SubscriptionBffResponse subscribe(Long userId, String email) {

        SubscriptionServiceRequest request = new SubscriptionServiceRequest(userId, email);

        String paymentServiceUrl = "http://localhost:8082/v1/subscriptions";

        ResponseEntity<SubscriptionBffResponse> response = restTemplate.postForEntity(
                paymentServiceUrl,
                request,
                SubscriptionBffResponse.class
        );

        return response.getBody();
    }
}
