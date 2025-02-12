package com.lima.GerenciamentoDePlanos.notification.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateNotificationDTO(
        @NotNull(message = "User ID é obrigatório")
        UUID userId,

        @NotBlank(message = "Tipo é obrigatório")
        String type,

        @NotBlank(message = "Mensagem é obrigatório")
        String message
) {}
