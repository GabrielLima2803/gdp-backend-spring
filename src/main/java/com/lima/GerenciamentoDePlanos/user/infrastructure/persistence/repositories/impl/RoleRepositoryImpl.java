package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.repositories.impl;

import com.lima.GerenciamentoDePlanos.user.domain.models.Role;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.RoleRepository;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.RoleJpaEntity;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper.RolePersistenceMapper;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.repositories.RoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleJpaRepository jpaRepository;
    private final RolePersistenceMapper mapper;

    public RoleRepositoryImpl(RoleJpaRepository jpaRepository, RolePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = mapper.toJpaEntity(role);
        RoleJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
