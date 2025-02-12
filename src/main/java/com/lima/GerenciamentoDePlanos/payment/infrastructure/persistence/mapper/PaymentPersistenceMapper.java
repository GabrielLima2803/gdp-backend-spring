package com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.payment.domain.models.Payment;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.entities.PaymentJpaEntity;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper.SubscriptionPersistenceMapper;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper.UserPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentPersistenceMapper {
    private final UserPersistenceMapper userMapper;
    private final SubscriptionPersistenceMapper subscriptionMapper;

    public PaymentPersistenceMapper(
            UserPersistenceMapper userMapper,
            SubscriptionPersistenceMapper subscriptionMapper
    ) {
        this.userMapper = userMapper;
        this.subscriptionMapper = subscriptionMapper;
    }

    public PaymentJpaEntity toJpaEntity(Payment domain) {
        return new PaymentJpaEntity(
                domain.getId(),
                userMapper.toJpaEntity(domain.getUser()),
                subscriptionMapper.toJpaEntity(domain.getSubscription()),
                domain.getAmount(),
                domain.getPaymentDate(),
                domain.getStatus()
        );
    }

    public Payment toDomain(PaymentJpaEntity jpaEntity) {
        return new Payment(
                jpaEntity.getId(),
                userMapper.toDomain(jpaEntity.getUser()),
                subscriptionMapper.toDomain(jpaEntity.getSubscription()),
                jpaEntity.getAmount(),
                jpaEntity.getPaymentDate(),
                jpaEntity.getStatus()
        );
    }
}
