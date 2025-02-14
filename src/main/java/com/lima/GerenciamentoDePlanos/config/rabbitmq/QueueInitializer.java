package com.lima.GerenciamentoDePlanos.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueInitializer {
    @Bean
    public CommandLineRunner initializeQueue(Queue notificationQueue) {
        return args -> {
            System.out.println("Fila criada: " + notificationQueue.getName());
        };
    }
}
