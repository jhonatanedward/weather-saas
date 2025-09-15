package com.edward.weatherbff.domain.port.out;

import com.edward.weatherbff.domain.model.weather.WeatherData;

public interface CachePort {
    WeatherData get(Long key);
    void save(Long key, WeatherData data);
}
