package com.edward.weatherbff.domain.exception;

public class AlreadyPremiumException extends RuntimeException{
    public AlreadyPremiumException(String message) {
        super(message);
    }
}
