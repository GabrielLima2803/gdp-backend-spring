package com.lima.GerenciamentoDePlanos.payment.application.dto;

import com.lima.GerenciamentoDePlanos.payment.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(
        Long id,
        UUID userId,
        Long subscriptionId,
        BigDecimal amount,
        LocalDateTime paymentDate,
        PaymentStatus status
) {}