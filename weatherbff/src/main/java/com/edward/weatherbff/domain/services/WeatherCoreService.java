package com.edward.weatherbff.domain.services;

import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.port.in.WeatherServicePort;
import com.edward.weatherbff.domain.port.out.CachePort;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import com.edward.weatherbff.domain.port.out.WeatherDataSourcePort;
import com.edward.weatherbff.domain.model.subscription.Plan;
import com.edward.weatherbff.domain.model.weather.Main;
import com.edward.weatherbff.domain.model.weather.WeatherData;

public class WeatherCoreService implements WeatherServicePort {
    private final WeatherDataSourcePort dataSource;
    private final CachePort cache;
    private final SubscriptionPort subscriptionPort;

    public WeatherCoreService(WeatherDataSourcePort dataSource, CachePort cache, SubscriptionPort subscriptionPort) {
        this.dataSource = dataSource;
        this.cache = cache;
        this.subscriptionPort = subscriptionPort;
    }

    public WeatherData getWeatherData(String cityId, String clientId) {

        Subscription subscription = subscriptionPort.getSubscription(clientId);

        // ... e o rate limit

        // E a lógica do cache (tudo aqui no Core)
        WeatherData cachedData = cache.get(clientId);
        if (cachedData != null) {
            return cachedData;
        }

        // Se não tem cache, pega os dados brutos e os converte
        WeatherData rawData = dataSource.fetchWeatherData(cityId);
        WeatherData filteredData = filterDataByPlan(rawData, subscription.getPlan().name());

        cache.save(clientId, filteredData);

        return filteredData;
    }

    /**
     * Filtra os dados de clima brutos com base no plano do cliente.
     * @param rawData O objeto de dados brutos da API externa.
     * @param plan O plano do cliente (ex: "free", "premium").
     * @return Um objeto WeatherData com os campos permitidos para o plano.
     */
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
