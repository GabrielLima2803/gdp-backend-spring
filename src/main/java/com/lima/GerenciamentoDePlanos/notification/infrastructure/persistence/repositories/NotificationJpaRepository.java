package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.repositories;

import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.entities.NotificationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationJpaRepository extends JpaRepository<NotificationJpaEntity, Long> {
    List<NotificationJpaEntity> findByUserId(UUID userId);
}
