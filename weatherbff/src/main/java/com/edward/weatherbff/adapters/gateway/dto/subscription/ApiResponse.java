package com.edward.weatherbff.adapters.gateway.dto.subscription;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}

