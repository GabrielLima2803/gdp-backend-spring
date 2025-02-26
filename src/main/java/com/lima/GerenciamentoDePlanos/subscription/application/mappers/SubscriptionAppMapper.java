package com.lima.GerenciamentoDePlanos.subscription.application.mappers;

import com.lima.GerenciamentoDePlanos.subscription.application.dto.SubscriptionInputDTO;
import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubscriptionAppMapper {

    public Subscription toDomain(SubscriptionInputDTO dto) {
        return new Subscription(
                null,
                dto.getName(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getDurationMonths(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
