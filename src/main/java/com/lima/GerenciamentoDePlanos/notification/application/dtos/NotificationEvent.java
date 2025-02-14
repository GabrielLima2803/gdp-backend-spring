package com.lima.GerenciamentoDePlanos.notification.application.dtos;

import java.util.UUID;

public record NotificationEvent(
        UUID userId,
        String type,
        String message
        
) {}