package com.lima.GerenciamentoDePlanos.user.application.mappers;

import com.lima.GerenciamentoDePlanos.user.application.dto.RegisterUserDTO;
import com.lima.GerenciamentoDePlanos.user.domain.emuns.UserStatus;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserRegisterAppMapper {

    public User toDomain(RegisterUserDTO dto) {
        return new User(
                null,
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                UserStatus.ACTIVE,
                null
        );
    }
}
