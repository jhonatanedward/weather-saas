package com.edward.weatherbff.adapters.gateway;

import com.edward.weatherbff.adapters.gateway.dto.weather.RawWeatherData;
import com.edward.weatherbff.domain.port.out.WeatherDataSourcePort;
import com.edward.weatherbff.domain.model.weather.WeatherData;
import com.edward.weatherbff.adapters.gateway.mappers.WeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class OpenWeatherAdapter  implements WeatherDataSourcePort {
    private final RestTemplate restTemplate;

    private final WeatherMapper weatherMapper;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Value("${openweathermap.apiurl}")
    private String apiUrl;

    public OpenWeatherAdapter(RestTemplate restTemplate, WeatherMapper weatherMapper) {
        this.restTemplate = restTemplate;
        this.weatherMapper = weatherMapper;
    }


    @Override
    public WeatherData fetchWeatherData(String cityId) {

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("id", cityId)
                .queryParam("appid", apiKey)
                .toUriString();

        try{
            RawWeatherData rawData = restTemplate.getForObject(url, RawWeatherData.class);

            if (rawData == null || rawData.getCod() != 200) {
                throw new RuntimeException("Erro ao buscar dados de clima.");
            }

            return weatherMapper.toDomainModel(rawData);
        }catch (RestClientException e) {
            // Imprime a mensagem de erro completa
            System.err.println("Erro na requisição ao OpenWeatherMap: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar dados de clima.", e);
        }


    }
}
