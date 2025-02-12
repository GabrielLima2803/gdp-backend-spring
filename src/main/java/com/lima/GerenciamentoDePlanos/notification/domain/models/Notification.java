package com.lima.GerenciamentoDePlanos.notification.domain.models;

import com.lima.GerenciamentoDePlanos.notification.domain.emuns.NotificationStatus;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private User user;
    private String type;
    private String message;
    private LocalDateTime createdAt;
    private NotificationStatus status = NotificationStatus.UNREAD;

    public Notification(User user, String type, String message) {
        this.user = user;
        this.type = type;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
