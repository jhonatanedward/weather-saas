package com.edward.weather_subscription_service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherSubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSubscriptionServiceApplication.class, args);
	}

}
