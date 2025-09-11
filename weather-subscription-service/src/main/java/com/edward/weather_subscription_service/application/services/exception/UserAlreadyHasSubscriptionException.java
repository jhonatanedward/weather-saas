package com.edward.weather_subscription_service.application.services.exception;

public class UserAlreadyHasSubscriptionException extends RuntimeException{
    public UserAlreadyHasSubscriptionException(String message) {
        super(message);
    }
}
