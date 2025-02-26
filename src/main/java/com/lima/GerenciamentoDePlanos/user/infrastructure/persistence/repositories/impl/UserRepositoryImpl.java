package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.repositories.impl;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.entities.SubscriptionJpaEntity;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper.SubscriptionPersistenceMapper;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.repositories.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;
    private final SubscriptionPersistenceMapper subscriptionPersistenceMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserPersistenceMapper mapper, SubscriptionPersistenceMapper subscriptionPersistenceMapper) {
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
        this.subscriptionPersistenceMapper = subscriptionPersistenceMapper;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = mapper.toJpaEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findBySubscription(Subscription subscription) {
        SubscriptionJpaEntity subscriptionJpaEntity = subscriptionPersistenceMapper.toJpaEntity(subscription);
        return userJpaRepository.findBySelectSubscription(subscriptionJpaEntity)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    @Override
    public List<User> saveAll(List<User> users) {
        List<UserJpaEntity> entities = users.stream()
                .map(mapper::toJpaEntity)
                .toList();
        List<UserJpaEntity> savedEntities = userJpaRepository.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
