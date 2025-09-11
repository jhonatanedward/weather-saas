package com.edward.weatherbff.adapters.gateway.mappers;

import com.edward.weatherbff.adapters.gateway.dto.RawWeatherData;
import com.edward.weatherbff.domain.model.weather.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    WeatherData toDomainModel(RawWeatherData rawData);
}