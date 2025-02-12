package com.lima.GerenciamentoDePlanos.notification.domain.repositories;

import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository {
    Notification save(Notification notification);
    List<Notification> findByUserId(UUID userId);
}
