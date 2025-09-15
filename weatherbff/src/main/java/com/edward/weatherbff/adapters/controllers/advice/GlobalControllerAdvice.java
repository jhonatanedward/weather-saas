package com.edward.weatherbff.adapters.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(exception = HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException httpClientErrorException) {
        ApiResponse repsonse = httpClientErrorException.getResponseBodyAs(ApiResponse.class);
        return ResponseEntity.status(httpClientErrorException.getStatusCode()).body(repsonse);
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        ApiResponse repsonse = ApiResponse.error(exception.getMessage());
        return ResponseEntity.status(500).body(repsonse);
    }

}
