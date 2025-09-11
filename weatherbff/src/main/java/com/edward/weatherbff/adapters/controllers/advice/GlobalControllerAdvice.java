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

}
