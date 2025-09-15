package com.edward.weather_subscription_service.infrastructure.adapter.queue;


import com.edward.weather_subscription_service.application.ports.out.MessageQueuePort;
import com.edward.weather_subscription_service.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAdapter implements MessageQueuePort {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message to RabbitMQ: " + message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
    }
}