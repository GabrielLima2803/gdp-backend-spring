package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging;

import com.lima.GerenciamentoDePlanos.config.rabbitmq.RabbitMQConfig;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(NotificationEvent event) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    event
            );
            log.info("Mensagem enviada com sucesso: {}", event);
        } catch (AmqpException e) {
            log.error("Falha ao enviar mensagem: {}", e.getMessage());
        }
    }
}
