package com.edward.weatherbff.adapters.controllers.mappers;

import com.edward.weatherbff.adapters.controllers.resources.SubscriptionBffResponse;
import com.edward.weatherbff.domain.model.subscription.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionControllerMapper {

    SubscriptionControllerMapper INSTANCE = Mappers.getMapper(SubscriptionControllerMapper.class);

    SubscriptionBffResponse subsciptionToDomainModel(Subscription subscription);
}
