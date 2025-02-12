package com.lima.GerenciamentoDePlanos.notification.application.usecases;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.CreateNotificationDTO;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationResponseDTO;
import com.lima.GerenciamentoDePlanos.notification.application.mappers.NotificationAppMapper;
import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;
import com.lima.GerenciamentoDePlanos.notification.domain.repositories.NotificationRepository;
import com.lima.GerenciamentoDePlanos.user.domain.exceptions.UserNotFoundException;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationUseCase {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationAppMapper mapper;

    public NotificationUseCase(NotificationRepository notificationRepository, UserRepository userRepository, NotificationAppMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    public NotificationResponseDTO createNotification(CreateNotificationDTO request) {
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));

        Notification notification = new Notification(
                user,
                request.type(),
                request.message()
        );
        Notification savedNotification = notificationRepository.save(notification);
        return mapper.convertToDTO(savedNotification);
    }
}
