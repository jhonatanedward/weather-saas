package com.edward.weather_subscription_service.infrastructure.adapter.controller.advice;

import com.edward.weather_subscription_service.application.services.exception.SubscriptionNotFoundException;
import com.edward.weather_subscription_service.application.services.exception.UserAlreadyHasSubscriptionException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiResponse<Void> apiResponse = ApiResponse.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(UserAlreadyHasSubscriptionException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyHasSubscriptionException(UserAlreadyHasSubscriptionException e) {
        ApiResponse<Void> apiResponse = ApiResponse.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleSubscriptionNotFoundException(SubscriptionNotFoundException e) {
        ApiResponse<Void> apiResponse = ApiResponse.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

}