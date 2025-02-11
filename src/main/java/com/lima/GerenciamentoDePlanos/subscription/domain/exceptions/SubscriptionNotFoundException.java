package com.lima.GerenciamentoDePlanos.subscription.domain.exceptions;

public class SubscriptionNotFoundException extends RuntimeException{
    public SubscriptionNotFoundException(Long subscriptionId) {
        super("Usuário não encontrado com ID: " + subscriptionId);
    }
}
