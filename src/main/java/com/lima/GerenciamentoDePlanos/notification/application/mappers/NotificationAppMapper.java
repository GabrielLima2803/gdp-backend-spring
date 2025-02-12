package com.lima.GerenciamentoDePlanos.notification.application.mappers;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationResponseDTO;
import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationAppMapper {
    public NotificationResponseDTO convertToDTO(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getUser().getId(),
                notification.getType(),
                notification.getMessage(),
                notification.getCreatedAt(),
                notification.getStatus().name()
        );
    }
}
