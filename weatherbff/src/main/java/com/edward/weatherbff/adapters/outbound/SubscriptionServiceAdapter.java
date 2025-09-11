package com.edward.weatherbff.adapters.outbound;

import com.edward.weatherbff.adapters.inbound.resources.SubscriptionBffResponse;
import com.edward.weatherbff.adapters.outbound.dto.SubscriptionServiceRequest;
import com.edward.weatherbff.application.port.SubscriptionServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionServiceAdapter implements SubscriptionServicePort {

    private final RestTemplate restTemplate;

    public SubscriptionServiceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SubscriptionBffResponse subscribe(Long userId, String email, String planType) {

        SubscriptionServiceRequest request = new SubscriptionServiceRequest(userId, email, planType);

        String paymentServiceUrl = "http://localhost:8082/v1/subscriptions";

        ResponseEntity<SubscriptionBffResponse> response = restTemplate.postForEntity(
                paymentServiceUrl,
                request,
                SubscriptionBffResponse.class
        );

        return response.getBody();
    }
}
