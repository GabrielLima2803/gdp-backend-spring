package com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.repositories.impl;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.domain.repositories.SubscriptionRepository;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.entities.SubscriptionJpaEntity;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper.SubscriptionPersistenceMapper;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.repositories.SubscriptionJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final SubscriptionJpaRepository jpaRepository;
    private final SubscriptionPersistenceMapper mapper;

    public SubscriptionRepositoryImpl(SubscriptionJpaRepository jpaRepository, SubscriptionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Subscription save(Subscription subscription) {
        SubscriptionJpaEntity entity = mapper.toJpaEntity(subscription);
        SubscriptionJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    @Override
    public Optional<Subscription> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    public List<Subscription> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    public List<Subscription> findByExpirationDateBeforeAndActiveTrue(LocalDateTime date) {
        return jpaRepository.findByExpirationDateBeforeAndActiveTrue(date)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Subscription> findExpiredSubscriptions(LocalDateTime now) {
        return jpaRepository.findExpiredSubscriptions(now)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
    public void delete(Subscription subscription) {
        jpaRepository.delete(mapper.toJpaEntity(subscription));
    }
}
