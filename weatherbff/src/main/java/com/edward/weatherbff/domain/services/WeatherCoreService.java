package com.edward.weatherbff.domain.services;

import com.edward.weatherbff.domain.exception.RateLimiteExceededException;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.port.in.WeatherServicePort;
import com.edward.weatherbff.domain.port.out.CachePort;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import com.edward.weatherbff.domain.port.out.WeatherDataSourcePort;
import com.edward.weatherbff.domain.model.subscription.Plan;
import com.edward.weatherbff.domain.model.weather.Main;
import com.edward.weatherbff.domain.model.weather.WeatherData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class WeatherCoreService implements WeatherServicePort {
    private final WeatherDataSourcePort dataSource;
    private final CachePort cache;
    private final SubscriptionPort subscriptionPort;

    public WeatherCoreService(WeatherDataSourcePort dataSource, CachePort cache, SubscriptionPort subscriptionPort) {
        this.dataSource = dataSource;
        this.cache = cache;
        this.subscriptionPort = subscriptionPort;
    }

    public WeatherData getWeatherData(String cityId, Long clientId) {

        String subscriptionPlanKey = "subscription-plan-" + clientId;
        Plan plan = cache.get(subscriptionPlanKey, Plan.class);

        if (plan == null) {
            Subscription subscription = subscriptionPort.getSubscription(clientId);
            plan = subscription.getPlan();
            cache.save(subscriptionPlanKey, plan, 24, TimeUnit.HOURS);
        }

        if (isRateLimited(clientId, plan)) {
            throw new RateLimiteExceededException("Rate limit exceeded for client " + clientId);
        }

        WeatherData cachedData = cache.get("weather-data-" + cityId, WeatherData.class);
        if (cachedData == null) {
            WeatherData rawData = dataSource.fetchWeatherData(cityId);
            cachedData = filterDataByPlan(rawData, plan.name());
        }

        cache.save("weather-data-" + cityId, cachedData, 15, TimeUnit.MINUTES);

        return cachedData;
    }
    private boolean isRateLimited(Long clientId, Plan plan) {
        String cacheKey = "rate-limit-" + clientId + "-" + LocalDate.now();

        Integer requestCount = cache.get(cacheKey, Integer.class);

        int maxRequests = getMaxRequestsByPlan(plan);
        long secondsUntilMidnight = ChronoUnit.SECONDS.between(
                java.time.LocalDateTime.now(),
                LocalDate.now().plusDays(1).atStartOfDay()
        );

        if (requestCount == null) {
            cache.save(cacheKey, 1, secondsUntilMidnight, TimeUnit.SECONDS);
            return false;
        }

        if (requestCount >= maxRequests) {
            return true;
        }

        cache.save(cacheKey, requestCount + 1, secondsUntilMidnight, TimeUnit.SECONDS);
        return false;
    }

    private int getMaxRequestsByPlan(Plan plan) {
        switch (plan) {
            case FREE:
                return 10;
            case PREMIUM:
                return 10000;
            default:
                return 0;
        }
    }

    private WeatherData filterDataByPlan(WeatherData rawData, String plan) {
        WeatherData filteredData = new WeatherData();

        filteredData.setCoord(rawData.getCoord());
        filteredData.setName(rawData.getName());
        Main filteredMain = new Main();
        filteredMain.setTemp(rawData.getMain().getTemp());
        filteredMain.setHumidity(rawData.getMain().getHumidity());
        filteredData.setMain(filteredMain);

        if (Plan.PREMIUM.name().equalsIgnoreCase(plan)) {
            filteredData.setWeather(rawData.getWeather());
            filteredData.setVisibility(rawData.getVisibility());
            filteredData.setWind(rawData.getWind());
            filteredMain.setFeels_like(rawData.getMain().getFeels_like());
            filteredMain.setPressure(rawData.getMain().getPressure());
        }

        return filteredData;
    }

}
