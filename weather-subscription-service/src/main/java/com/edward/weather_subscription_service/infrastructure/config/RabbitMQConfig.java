package com.edward.weather_subscription_service.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "subscription-events-queue";

    @Bean
    public Queue subscriptionEventsQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
