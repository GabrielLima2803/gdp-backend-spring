package com.lima.GerenciamentoDePlanos.notification.application.teste;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging.NotificationProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationTest {

    @Autowired
    private NotificationProducer producer;



    @Autowired
    private RabbitTemplate rabbitTemplate;



}
