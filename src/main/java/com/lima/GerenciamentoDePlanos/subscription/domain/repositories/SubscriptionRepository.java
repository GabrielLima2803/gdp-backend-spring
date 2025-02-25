package com.lima.GerenciamentoDePlanos.subscription.domain.repositories;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();
    List<Subscription> findByExpirationDateBeforeAndActiveTrue(LocalDateTime date);
    List<Subscription> findExpiredSubscriptions(LocalDateTime now);
    List<Subscription> findByExpirationDateBetween(LocalDateTime start, LocalDateTime end);

    void delete(Subscription subscription);

}
