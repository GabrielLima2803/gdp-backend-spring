package com.lima.GerenciamentoDePlanos.subscription.application.usecases;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.subscription.domain.repositories.SubscriptionRepository;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionExpirationUseCase {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionExpirationUseCase(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
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
}
