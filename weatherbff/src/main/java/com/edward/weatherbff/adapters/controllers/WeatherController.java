package com.edward.weatherbff.adapters.controllers;

import com.edward.weatherbff.domain.model.weather.WeatherData;
import com.edward.weatherbff.domain.port.in.SubscriptionServicePort;
import com.edward.weatherbff.domain.port.in.WeatherServicePort;
import com.edward.weatherbff.domain.port.out.CachePort;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherServicePort weatherService;

    @GetMapping("/{cityId}")
    public WeatherData getWeather(@PathVariable String cityId, @RequestHeader("Client-Id") String clientId) {
        return weatherService.getWeatherData(cityId, clientId);
    }
}
