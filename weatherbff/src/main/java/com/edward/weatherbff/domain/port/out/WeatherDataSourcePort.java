package com.edward.weatherbff.domain.port.out;

import com.edward.weatherbff.domain.model.weather.WeatherData;

public interface WeatherDataSourcePort {
    WeatherData fetchWeatherData(String cityId);
}
