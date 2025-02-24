package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging;

import com.lima.GerenciamentoDePlanos.config.rabbitmq.RabbitMQConfig;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.notification.application.usecases.EmailUseCase;
import com.lima.GerenciamentoDePlanos.notification.application.usecases.NotificationUseCase;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Slf4j
@Component
public class NotificationConsumer {
    private final NotificationUseCase notificationUseCase;
    private final EmailUseCase emailUseCase;
    private final UserRepository userRepository;

    public NotificationConsumer(NotificationUseCase notificationUseCase, EmailUseCase emailUseCase, UserRepository userRepository) {
        this.notificationUseCase = notificationUseCase;
        this.emailUseCase = emailUseCase;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    @Transactional
    public void handleNotificationEvent(NotificationEvent event) {
        try {
            notificationUseCase.processNotification(event);

            User user = userRepository.findById(event.userId()).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));

            String subject = "Notificação de " + event.type() + " para " + user.getUsername();
            String message = "Olá, " + user.getUsername() + "!\n\n" + event.message();
            log.info("Enviando e-mail para: {}", user.getEmail());
            emailUseCase.sendMail(user.getEmail(), subject, message);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Erro irreversível");
        }
    }

}
