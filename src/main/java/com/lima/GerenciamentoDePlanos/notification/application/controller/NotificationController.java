package com.lima.GerenciamentoDePlanos.notification.application.controller;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.CreateNotificationDTO;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationResponseDTO;
import com.lima.GerenciamentoDePlanos.notification.application.usecases.NotificationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationUseCase notificationUseCase;

    public NotificationController(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody @Valid CreateNotificationDTO request) {
        notificationUseCase.createAndQueueNotification(request);
        return ResponseEntity.accepted().build();
    }
}
