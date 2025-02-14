package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging;

import com.lima.GerenciamentoDePlanos.config.rabbitmq.RabbitMQConfig;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.notification.application.usecases.NotificationUseCase;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class NotificationConsumer {
    private final NotificationUseCase notificationUseCase;

    public NotificationConsumer(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleNotificationEvent(NotificationEvent event) {
        try {
            notificationUseCase.processNotification(event);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Erro irreversível");
        }
    }
}
