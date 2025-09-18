package com.edward.weather_subscription_service.infrastructure.adapter.repository;

import com.edward.weather_subscription_service.domain.model.Plan;
import com.edward.weather_subscription_service.application.ports.out.SubscriptionRepositoryPort;
import com.edward.weather_subscription_service.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

interface JpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
    Optional<SubscriptionEntity> findByUserId(Long userId);
    Optional<SubscriptionEntity> findByExternalSubscriptionId(String externalSubscriptionId);
}

@Repository
public class JpaSubscriptionRepositoryAdapter implements SubscriptionRepositoryPort {

    private final JpaSubscriptionRepository repository;

    public JpaSubscriptionRepositoryAdapter(JpaSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription save(Subscription subscription) {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setId(subscription.getId());
        entity.setUserId(subscription.getUserId());
        entity.setPlan(subscription.getPlan().name());
        entity.setExternalSubscriptionId(subscription.getExternalSubscriptionId());
        entity.setCheckoutUrl(subscription.getCheckoutUrl());
        entity.setPaymentDate(subscription.getPaymentDate());
        entity.setStatus(subscription.getStatus());
        repository.save(entity);
        subscription.setId(entity.getId());
        return subscription;
    }

    @Override
    public Subscription findByUserId(Long userId) {
        return repository.findByUserId(userId)
                .map(this::toDomainModel)
                .orElse(null);
    }

    @Override
    public Subscription findByExternalSubscriptionId(String externalSubscriptionId) {
        return repository.findByExternalSubscriptionId(externalSubscriptionId)
                .map(this::toDomainModel)
                .orElse(null);
    }

    private Subscription toDomainModel(SubscriptionEntity entity) {

        Subscription subscription = new Subscription(
                entity.getUserId(),
                entity.getExternalSubscriptionId(),
                Plan.valueOf(entity.getPlanType()),
                entity.getCheckoutUrl()
        );
        subscription.setId(entity.getId());
        subscription.setPaymentDate(entity.getPaymentDate());
        subscription.setStatus(entity.getStatus());
        return subscription;
    }
}