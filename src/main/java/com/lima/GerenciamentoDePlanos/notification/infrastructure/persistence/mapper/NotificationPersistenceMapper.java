package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.mapper;

import com.lima.GerenciamentoDePlanos.notification.domain.models.Notification;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.entities.NotificationJpaEntity;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.mapper.UserPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationPersistenceMapper {
    private final UserPersistenceMapper userMapper;

    public NotificationPersistenceMapper(UserPersistenceMapper userMapper) {
        this.userMapper = userMapper;
    }

    public NotificationJpaEntity toJpaEntity(Notification domain) {
        return new NotificationJpaEntity(
                userMapper.toJpaEntity(domain.getUser()),
                domain.getType(),
                domain.getMessage()
        );
    }
    public Notification toDomain(NotificationJpaEntity jpaEntity) {
        return new  Notification(
            userMapper.toDomain(jpaEntity.getUser()),
            jpaEntity.getType(),
            jpaEntity.getMessage()
        );
    }
}
