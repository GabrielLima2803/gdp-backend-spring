package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserPersistenceMapper {
    private final RolePersistenceMapper roleMapper;

    public UserPersistenceMapper(RolePersistenceMapper roleMapper) {
        this.roleMapper = roleMapper;
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
                jpaEntity.getStatus(),
                jpaEntity.getRoles().stream()
                        .map(roleMapper::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
