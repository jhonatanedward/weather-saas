package com.edward.weatherbff.adapters.consumers;

import com.edward.weatherbff.adapters.consumers.dto.SubscriptionPaid;
import com.edward.weatherbff.config.RabbitMQConfig;
import com.edward.weatherbff.domain.port.out.CachePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
public class SubscriptionEventsListener {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionEventsListener.class);
    private final CachePort cache;
    private final ObjectMapper objectMapper;

    public SubscriptionEventsListener(
            CachePort cache,
            ObjectMapper objectMapper) {
        this.cache = cache;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        try {
            SubscriptionPaid subscriptionPaid = objectMapper.readValue(message, SubscriptionPaid.class);

            String subscriptionPlanKey = "subscription-plan-" + subscriptionPaid.getUserId();
            cache.save(subscriptionPlanKey, subscriptionPaid.getPlan(), 24, TimeUnit.HOURS);

            String rateLimitKey = "rate-limit-" + subscriptionPaid.getUserId() + "-" + LocalDate.now();
            cache.save(rateLimitKey, 0, 24, TimeUnit.HOURS);

            logger.info(
                    "Plano do cliente {} atualizado para {} e contador de requisições resetado no Redis.",
                    subscriptionPaid.getUserId(), subscriptionPaid.getPlan());

        } catch (Exception e) {
            logger.error("Falha ao processar evento de pagamento.. Erro: {}", e.getMessage());
        }
    }
}