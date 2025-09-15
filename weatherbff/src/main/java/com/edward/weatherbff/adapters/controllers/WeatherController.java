package com.edward.weatherbff.adapters.controllers;

import com.edward.weatherbff.domain.model.weather.WeatherData;
import com.edward.weatherbff.domain.port.in.WeatherServicePort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherServicePort weatherService;

    @GetMapping("/{cityId}")
    public WeatherData getWeather(@PathVariable String cityId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return weatherService.getWeatherData(cityId, userId);
    }
}
