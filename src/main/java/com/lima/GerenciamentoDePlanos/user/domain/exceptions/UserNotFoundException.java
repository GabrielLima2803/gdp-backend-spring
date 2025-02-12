package com.lima.GerenciamentoDePlanos.user.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID userId) {
        super("Usuário não encontrado com ID: " + userId);
    }
}
