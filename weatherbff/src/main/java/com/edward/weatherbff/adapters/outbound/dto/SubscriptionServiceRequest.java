package com.edward.weatherbff.adapters.outbound.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionServiceRequest {
    private Long userId;
    private String email;
    private String planType;
}
