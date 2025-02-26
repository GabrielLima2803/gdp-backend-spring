package com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.repositories;

import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.entities.PaymentJpaEntity;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
    List<PaymentJpaEntity> findByUserId(UUID uuid);
    List<PaymentJpaEntity> findByUserEmail(String email);
}
