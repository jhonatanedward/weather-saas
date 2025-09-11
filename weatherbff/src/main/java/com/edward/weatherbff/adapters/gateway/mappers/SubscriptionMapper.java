package com.edward.weatherbff.adapters.gateway.mappers;

import com.edward.weatherbff.adapters.gateway.dto.Data;
import com.edward.weatherbff.domain.model.subscription.SubscriptionCreated;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionCreated toDomainModel(Data subscriptionResponse);
}