package com.lima.GerenciamentoDePlanos.subscription.domain.repositories;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();

}
