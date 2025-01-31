package com.lima.GerenciamentoDePlanos.user.domain.repositories;

import com.lima.GerenciamentoDePlanos.user.domain.models.Role;

import java.util.Optional;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
}
