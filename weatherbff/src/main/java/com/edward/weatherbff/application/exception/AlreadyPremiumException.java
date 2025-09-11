package com.edward.weatherbff.application.exception;

public class AlreadyPremiumException extends RuntimeException{
    public AlreadyPremiumException(String message) {
        super(message);
    }
}
