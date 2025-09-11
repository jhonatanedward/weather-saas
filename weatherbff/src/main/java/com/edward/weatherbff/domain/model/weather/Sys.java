package com.edward.weatherbff.domain.model.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sys {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
    // Getters e Setters
}
