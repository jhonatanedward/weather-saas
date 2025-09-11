package com.edward.weatherbff.domain.port.out;

import com.edward.weatherbff.domain.model.weather.WeatherData;

public interface CachePort {
    WeatherData get(String key);
    void save(String key, WeatherData data);
}
