package com.edward.weatherbff.adapters.gateway.mappers;


import com.edward.weatherbff.adapters.gateway.dto.subscription.SubscriptionCreatedResponse;
import com.edward.weatherbff.adapters.gateway.dto.subscription.SubscriptionData;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionCreated toCreatedDomainModel(SubscriptionCreatedResponse subscriptionResponse);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "planType", target = "plan")
    Subscription toDomainModel(SubscriptionData subscriptionResponse);
}