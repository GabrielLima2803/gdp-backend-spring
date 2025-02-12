package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.repositories.impl;

import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;
import com.lima.GerenciamentoDePlanos.notification.domain.repositories.NotificationRepository;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.entities.NotificationJpaEntity;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.mapper.NotificationPersistenceMapper;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.repositories.NotificationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
    private final NotificationJpaRepository jpaRepository;
    private final NotificationPersistenceMapper mapper;

    public NotificationRepositoryImpl(NotificationJpaRepository jpaRepository, NotificationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    public Notification save(Notification notification) {
        NotificationJpaEntity entity = mapper.toJpaEntity(notification);
        NotificationJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Notification> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
