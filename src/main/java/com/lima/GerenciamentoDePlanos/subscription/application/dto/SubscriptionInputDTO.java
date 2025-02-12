package com.lima.GerenciamentoDePlanos.subscription.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public class SubscriptionInputDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Duration months is required")
    @Positive(message = "Duration months must be positive")
    private Integer durationMonths;

    public SubscriptionInputDTO() {
    }

    public SubscriptionInputDTO(String name, BigDecimal price, String description, Integer durationMonths) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.durationMonths = durationMonths;
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
}