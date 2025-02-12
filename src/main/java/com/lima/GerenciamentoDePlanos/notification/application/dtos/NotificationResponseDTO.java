package com.lima.GerenciamentoDePlanos.notification.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponseDTO(
        Long id,
        UUID userId,
        String type,
        String message,
        LocalDateTime createdAt,
        String status
) {}