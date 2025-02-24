package com.lima.GerenciamentoDePlanos.user.application.mappers;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.user.application.dto.RegisterUserDTO;
import com.lima.GerenciamentoDePlanos.user.domain.emuns.UserStatus;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class UserRegisterAppMapper {

    Subscription freeSubscription = new Subscription(
            1L,
            "Free",
            new BigDecimal("0.00"),
            "Plano Free",
            0,
            LocalDateTime.now(),
            null
    );

    public User toDomain(RegisterUserDTO dto) {
        return new User(
                null,
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                freeSubscription,
                UserStatus.ACTIVE,
                null
        );
    }
}
