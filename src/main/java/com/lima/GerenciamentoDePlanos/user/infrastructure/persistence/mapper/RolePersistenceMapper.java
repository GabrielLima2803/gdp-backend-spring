package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.user.domain.models.Role;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.RoleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePersistenceMapper {

    public RoleJpaEntity toJpaEntity(Role domain) {
        return new RoleJpaEntity(
                domain.getId(),
                domain.getName()
        );
    }
    public Role toDomain(RoleJpaEntity jpaEntity) {
        return new Role(
                jpaEntity.getId(),
                jpaEntity.getName()
        );
    }
}
