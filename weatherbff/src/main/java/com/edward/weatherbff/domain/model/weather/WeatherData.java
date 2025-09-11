package com.edward.weatherbff.domain.model.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherData {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private int visibility;
    private Sys sys;
    private String name;

}


