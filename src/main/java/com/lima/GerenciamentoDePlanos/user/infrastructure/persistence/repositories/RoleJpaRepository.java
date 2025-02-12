package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.repositories;

import com.lima.GerenciamentoDePlanos.user.domain.models.Role;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, Long> {
    Optional<RoleJpaEntity> findByName(String name);
}
