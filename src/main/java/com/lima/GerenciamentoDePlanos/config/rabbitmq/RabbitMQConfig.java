package com.lima.GerenciamentoDePlanos.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Nomes centralizados
    public static final String QUEUE_NAME = "notification_queue";
    public static final String EXCHANGE_NAME = "notification_exchange";
    public static final String ROUTING_KEY = "notification_routing_key";
    public static final String DLQ_NAME = "notification_dlq";

    // Fila principal
    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }
    // Dead Letter Queue
    @Bean
    public Queue dlq() {
        return new Queue(DLQ_NAME, true, false, false);
    }

    // Exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    // Binding principal
    @Bean
    public Binding bindingNotificationQueue() {
        return BindingBuilder.bind(notificationQueue())
                .to(directExchange())
                .with(ROUTING_KEY);
    }

    // Configuração do Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate corrigido
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        template.setExchange(EXCHANGE_NAME); // Usa a exchange correta
        template.setRoutingKey(ROUTING_KEY); // Usa a routing key definida
        return template;
    }

    // Configuração do Listener
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        return factory;
    }
}
