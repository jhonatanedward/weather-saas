package com.edward.weatherbff.adapters.gateway;

import com.edward.weatherbff.adapters.gateway.dto.RawWeatherData;
import com.edward.weatherbff.domain.port.out.WeatherDataSourcePort;
import com.edward.weatherbff.domain.model.weather.WeatherData;
import com.edward.weatherbff.adapters.gateway.mappers.WeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OpenWeatherAdapter  implements WeatherDataSourcePort {
    private final RestTemplate restTemplate;

    private final WeatherMapper weatherMapper;

    @Value("openweathermap.apikey")
    private String apiKey;

    @Value("openweathermap.apiurl")
    private String apiUrl;

    public OpenWeatherAdapter(RestTemplate restTemplate, WeatherMapper weatherMapper) {
        this.restTemplate = restTemplate;
        this.weatherMapper = weatherMapper;
    }


    @Override
    public WeatherData fetchWeatherData(String cityId) {

        RawWeatherData rawData = restTemplate.getForObject(apiUrl, RawWeatherData.class, Map.of("id", cityId, "appid", apiKey));

        if (rawData == null || rawData.getCod() != 200) {
            throw new RuntimeException("Erro ao buscar dados de clima.");
        }

        return weatherMapper.toDomainModel(rawData);
    }
}
