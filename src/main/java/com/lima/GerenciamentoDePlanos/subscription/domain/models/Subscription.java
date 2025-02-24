package com.lima.GerenciamentoDePlanos.subscription.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Subscription {
    private final Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer durationMonths;
    private LocalDateTime createdAt;
    private boolean active = true;
    private LocalDateTime expirationDate;
    private LocalDateTime updatedAt;

    public Subscription(Long id, String name, BigDecimal price, String description, Integer durationMonths, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.durationMonths = durationMonths;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
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

    public void updateDetails(String name, BigDecimal price, String description, Integer durationMonths) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.durationMonths = durationMonths;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isValidSubscription() {
        return LocalDateTime.now().isBefore(createdAt.plusMonths(durationMonths));
    }
}
