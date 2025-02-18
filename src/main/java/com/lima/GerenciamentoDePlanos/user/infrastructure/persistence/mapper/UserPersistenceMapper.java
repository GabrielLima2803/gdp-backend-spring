package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper.SubscriptionPersistenceMapper;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserPersistenceMapper {
    private final RolePersistenceMapper roleMapper;
    private final SubscriptionPersistenceMapper subscriptionMapper;

    public UserPersistenceMapper(RolePersistenceMapper roleMapper, SubscriptionPersistenceMapper subscriptionMapper) {
        this.roleMapper = roleMapper;
        this.subscriptionMapper = subscriptionMapper;
    }
    public UserJpaEntity toJpaEntity(User domain) {
        return new UserJpaEntity(
                domain.getId(),
                domain.getUsername(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                subscriptionMapper.toJpaEntity(domain.getSelectSubscription()),
                domain.getRoles().stream()
                        .map(roleMapper::toJpaEntity)
                        .collect(Collectors.toSet())
        );
    }

    public User toDomain(UserJpaEntity jpaEntity) {
        return new User(
                jpaEntity.getId(),
                jpaEntity.getUsername(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword(),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                subscriptionMapper.toDomain(jpaEntity.getSelectSubscription()),
                jpaEntity.getStatus(),
                jpaEntity.getRoles().stream()
                        .map(roleMapper::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
