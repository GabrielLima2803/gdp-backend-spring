package com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.repositories.impl;

import com.lima.GerenciamentoDePlanos.payment.domain.models.Payment;
import com.lima.GerenciamentoDePlanos.payment.domain.repositories.PaymentRepository;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.entities.PaymentJpaEntity;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.mapper.PaymentPersistenceMapper;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.repositories.PaymentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository jpaRepository;
    private final PaymentPersistenceMapper mapper;

    public PaymentRepositoryImpl(PaymentJpaRepository jpaRepository, PaymentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Payment save(Payment payment) {
        PaymentJpaEntity entity = mapper.toJpaEntity(payment);
        PaymentJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    @Override
    public List<Payment> findByUserId(UUID id) {
        return jpaRepository.findByUserId(id)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
    @Override
    public List<Payment> findByUserEmail(String email) {
        return jpaRepository.findByUserEmail(email)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
    @Override
    public List<Payment> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


}
