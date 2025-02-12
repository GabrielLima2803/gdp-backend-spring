package com.lima.GerenciamentoDePlanos.subscription.application.usecases;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.domain.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionUseCase(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createSubscription(Subscription subscription) {
        if (subscription.getName() == null || subscription.getName().isBlank()) {
            throw new IllegalArgumentException("Subscription name is required");
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

}