package com.lima.GerenciamentoDePlanos.user.domain.repositories;

import com.lima.GerenciamentoDePlanos.user.domain.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
