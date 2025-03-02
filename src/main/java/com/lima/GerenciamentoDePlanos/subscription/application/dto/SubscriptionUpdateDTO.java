package com.lima.GerenciamentoDePlanos.subscription.application.dto;

import java.math.BigDecimal;

public class SubscriptionUpdateDTO {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer durationMonths;

    // Getters e Setters
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
