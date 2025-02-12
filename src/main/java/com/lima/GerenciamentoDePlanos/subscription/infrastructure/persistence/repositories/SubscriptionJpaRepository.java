package com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.repositories;

import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.entities.SubscriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionJpaEntity, Long> {
}
