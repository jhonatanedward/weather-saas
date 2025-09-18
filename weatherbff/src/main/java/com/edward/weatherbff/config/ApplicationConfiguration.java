package com.edward.weatherbff.config;

import com.edward.weatherbff.domain.port.out.CachePort;
import com.edward.weatherbff.domain.port.out.SubscriptionPort;
import com.edward.weatherbff.domain.port.out.WeatherDataSourcePort;
import com.edward.weatherbff.domain.services.SubscriptionService;
import com.edward.weatherbff.domain.services.WeatherCoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@org.springframework.context.annotation.Configuration
public class ApplicationConfiguration {

    @Value("${auth.url}")
    private String authUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherCoreService weatherCoreService(
            WeatherDataSourcePort dataSource,
            CachePort cache,
            SubscriptionPort subscriptionPort) {
        return new WeatherCoreService(dataSource, cache, subscriptionPort);
    }

    @Bean
    public SubscriptionService subscriptionService(
            SubscriptionPort subscriptionPort
    ) {
        return new SubscriptionService(subscriptionPort);
    }

    @Bean
    public PublicKey publicKey(RestTemplate restTemplate) throws Exception {
        String keyString = restTemplate.getForObject(authUrl + "/v1/auth/public-key", String.class);
        keyString = keyString
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

}
