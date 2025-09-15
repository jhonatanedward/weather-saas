package com.edward.weatherbff.adapters.cache;

import com.edward.weatherbff.domain.port.out.CachePort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheAdapter implements CachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheAdapter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    @Override
    public void save(String key, Object data, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, data, timeout, unit);
    }
}