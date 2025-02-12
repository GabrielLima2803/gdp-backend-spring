package com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.entities.SubscriptionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPersistenceMapper {

    public SubscriptionJpaEntity toJpaEntity(Subscription domain) {
        return new SubscriptionJpaEntity(
                domain.getId(),
                domain.getName(),
                domain.getPrice(),
                domain.getDescription(),
                domain.getDurationMonths(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public Subscription toDomain(SubscriptionJpaEntity jpaEntity) {
        Subscription subscription = new Subscription(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getPrice(),
                jpaEntity.getDescription(),
                jpaEntity.getDurationMonths(),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt()
        );
        return subscription;
    }
}
