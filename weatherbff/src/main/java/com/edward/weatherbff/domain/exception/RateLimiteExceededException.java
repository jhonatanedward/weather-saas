package com.edward.weatherbff.domain.exception;

public class RateLimiteExceededException extends RuntimeException{
    public RateLimiteExceededException(String message) { super(message); }
}
