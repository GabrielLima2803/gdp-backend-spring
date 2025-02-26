package com.lima.GerenciamentoDePlanos.subscription.application.usecases;

import com.lima.GerenciamentoDePlanos.config.rabbitmq.RabbitMQConfig;
import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.domain.repositories.SubscriptionRepository;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionExpirationUseCase {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final RabbitTemplate rabbitTemplate;

    public SubscriptionExpirationUseCase(UserRepository userRepository, SubscriptionRepository subscriptionRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.rabbitTemplate = rabbitTemplate;
    }
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkSubscriptionExpiration() {
        LocalDateTime now = LocalDateTime.now();
        Subscription freeSubscription = subscriptionRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Free subscription not found"));
        List<Subscription> expiredSubscriptions = subscriptionRepository
                .findByExpirationDateBeforeAndActiveTrue(now);

        expiredSubscriptions.forEach(subscription -> {
            List<User> users = userRepository.findBySubscription(subscription);

            users.forEach(user -> {
                user.setSelectSubscription(freeSubscription);
            });
            subscriptionRepository.save(subscription);
            userRepository.saveAll(users);
        });
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void notifyExpiringSubscriptions() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusDays(1);

        List<Subscription> expiringSubscriptions = subscriptionRepository.findByExpirationDateBetween(now, threshold);

        expiringSubscriptions.forEach(subscription -> {
            List<User> users = userRepository.findBySubscription(subscription);
            users.forEach(user -> {
                NotificationEvent event = new NotificationEvent(
                        user.getId(),
                        "Assinatura Prestes a Vencer",
                        "Olá, " + user.getUsername() + "! Sua assinatura expirará em breve. Por favor, renove para continuar usufruindo dos serviços."
                );
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, event);
            });
        });
    }
}
