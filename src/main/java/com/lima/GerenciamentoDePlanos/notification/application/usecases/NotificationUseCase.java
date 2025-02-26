package com.lima.GerenciamentoDePlanos.notification.application.usecases;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.CreateNotificationDTO;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationResponseDTO;
import com.lima.GerenciamentoDePlanos.notification.application.mappers.NotificationAppMapper;
import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;
import com.lima.GerenciamentoDePlanos.notification.domain.repositories.NotificationRepository;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging.NotificationProducer;
import com.lima.GerenciamentoDePlanos.user.domain.exceptions.UserNotFoundException;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationUseCase {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationAppMapper mapper;
    private final NotificationProducer notificationProducer;

    public NotificationUseCase(NotificationRepository notificationRepository, UserRepository userRepository, NotificationAppMapper mapper, NotificationProducer notificationProducer) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
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

    public void createAndQueueNotification(CreateNotificationDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));

        NotificationEvent event = new NotificationEvent(
                user.getId(),
                request.type(),
                request.message()
        );
        notificationProducer.sendNotification(event);
    }

    @Transactional
    public void processNotification(NotificationEvent event) {
        Notification notification = new Notification(
                userRepository.findById(event.userId()).get(),
                event.type(),
                event.message()
        );
        notificationRepository.save(notification);
    }
}
