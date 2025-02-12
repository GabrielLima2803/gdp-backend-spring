package com.lima.GerenciamentoDePlanos.payment.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentDTO(
        @NotNull(message = "User ID é obrigatório")
        UUID userId,

        @NotNull(message = "Subscription ID é obrigatório")
        Long subscriptionId,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        BigDecimal amount
) {}