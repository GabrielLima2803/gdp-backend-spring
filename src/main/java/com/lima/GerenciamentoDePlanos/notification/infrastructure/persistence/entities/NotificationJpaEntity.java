package com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.entities;

import com.lima.GerenciamentoDePlanos.notification.domain.emuns.NotificationStatus;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
public class NotificationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status = NotificationStatus.UNREAD;

    public NotificationJpaEntity(UserJpaEntity user, String type, String message) {
        this.user = user;
        this.type = type;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}
