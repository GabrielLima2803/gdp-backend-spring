package com.lima.GerenciamentoDePlanos.user.domain.models;

import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.user.domain.emuns.UserStatus;
import com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities.UserJpaEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus status = UserStatus.ACTIVE;
    private Subscription selectSubscription;
    private Set<Role> roles = new HashSet<>();

    public User(UUID id, String username, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Subscription selectSubscription, UserStatus status, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.selectSubscription = selectSubscription;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Subscription getSelectSubscription() {
        return selectSubscription;
    }

    public void setSelectSubscription(Subscription selectSubscription) {
        this.selectSubscription = selectSubscription;
    }
}
