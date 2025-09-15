package com.edward.weatherbff.domain.port.in;

import com.edward.weatherbff.domain.model.weather.WeatherData;

public interface WeatherServicePort {
    WeatherData getWeatherData(String cityId, Long clientId);
}