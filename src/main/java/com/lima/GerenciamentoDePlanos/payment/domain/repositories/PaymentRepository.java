package com.lima.GerenciamentoDePlanos.payment.domain.repositories;

import com.lima.GerenciamentoDePlanos.payment.domain.models.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findById(Long id);
    Optional<Payment> findByUserId(UUID id);

    List<Payment> findAll();
}
