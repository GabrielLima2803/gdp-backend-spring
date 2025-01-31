package com.lima.GerenciamentoDePlanos.subscription.application.controller;

import com.lima.GerenciamentoDePlanos.subscription.application.dto.SubscriptionInputDTO;
import com.lima.GerenciamentoDePlanos.subscription.application.mappers.SubscriptionAppMapper;
import com.lima.GerenciamentoDePlanos.subscription.application.usecases.SubscriptionUseCase;
import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.infrastructure.persistence.mapper.SubscriptionPersistenceMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;
    private final SubscriptionAppMapper appMapper;

    public SubscriptionController(SubscriptionUseCase subscriptionUseCase, SubscriptionAppMapper appMapper) {
        this.subscriptionUseCase = subscriptionUseCase;
        this.appMapper = appMapper;
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody @Valid SubscriptionInputDTO input) {
        Subscription subscription = appMapper.toDomain(input);
        Subscription saved = subscriptionUseCase.createSubscription(subscription);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionUseCase.getAllSubscriptions());
    }
}
