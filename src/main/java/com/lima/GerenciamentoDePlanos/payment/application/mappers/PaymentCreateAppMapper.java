package com.lima.GerenciamentoDePlanos.payment.application.mappers;

import com.lima.GerenciamentoDePlanos.payment.application.dto.PaymentResponseDTO;
import com.lima.GerenciamentoDePlanos.payment.domain.models.Payment;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.entities.PaymentJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentCreateAppMapper {

    public PaymentResponseDTO toResponseDTO(Payment entity) {
        return  new PaymentResponseDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getSubscription().getId(),
                entity.getAmount(),
                entity.getPaymentDate(),
                entity.getStatus()
        );
    }
}
