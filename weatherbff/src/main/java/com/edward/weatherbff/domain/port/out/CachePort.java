package com.edward.weatherbff.domain.port.out;

import com.edward.weatherbff.domain.model.weather.WeatherData;

import java.util.concurrent.TimeUnit;

public interface CachePort {
    <T> T get(String key, Class<T> type);

    void save(String key, Object data, long timeout, TimeUnit unit);
}
