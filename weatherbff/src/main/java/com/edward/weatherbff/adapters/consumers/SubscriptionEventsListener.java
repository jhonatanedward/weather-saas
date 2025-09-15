package com.edward.weatherbff.adapters.consumers;

import com.edward.weatherbff.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionEventsListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("BFF recebeu uma mensagem do RabbitMQ: " + message);
    }
}