package com.edward.weatherbff;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class WeatherbffApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherbffApplication.class, args);
	}

}
