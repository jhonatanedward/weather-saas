package com.edward.weatherbff.domain.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sys {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
    // Getters e Setters
}
