package com.edward.weather_subscription_service.infrastructure.adapter.controller.resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;


public record PaymentProcessed (String externalSubscriptionId,LocalDateTime paymentEffectedDate){
}
