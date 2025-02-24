package com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.repositories;

import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.entities.SubscriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionJpaEntity, Long> {
    List<SubscriptionJpaEntity> findByExpirationDateBeforeAndActiveTrue(LocalDateTime date);

    @Query("SELECT s FROM SubscriptionJpaEntity s WHERE s.active = true AND s.expirationDate < :now")
    List<SubscriptionJpaEntity> findExpiredSubscriptions(@Param("now") LocalDateTime now);

}
