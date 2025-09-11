package com.edward.weatherbff.adapters.gateway.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionResponse {
    private String status;
    private String message;
    private Data data;
}

