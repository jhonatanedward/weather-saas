package com.edward.weather_subscription_service.application.ports.out;


public interface MessageQueuePort {
    void sendMessage(String message);
}