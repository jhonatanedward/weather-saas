package com.edward.weatherbff.adapters.cache;

import com.edward.weatherbff.domain.port.out.CachePort;
import com.edward.weatherbff.domain.model.weather.WeatherData;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheAdapter implements CachePort {

    @Override
    public WeatherData get(Long key) {
        return null;
    }
    @Override
    public void save(Long key, WeatherData data) {

    }

}
