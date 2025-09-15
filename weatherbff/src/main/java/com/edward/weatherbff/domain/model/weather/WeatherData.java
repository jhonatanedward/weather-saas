package com.edward.weatherbff.domain.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherData {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private int visibility;
    private Sys sys;
    private String name;

}


