package com.lima.GerenciamentoDePlanos.payment.domain.models;

import com.lima.GerenciamentoDePlanos.payment.domain.enums.PaymentStatus;
import com.lima.GerenciamentoDePlanos.subscription.domain.models.Subscription;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private final Long id;
    private final User user;
    private final Subscription subscription;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status = PaymentStatus.REFUNDED;

    public Payment(Long id, User user, Subscription subscription, BigDecimal amount, LocalDateTime paymentDate, PaymentStatus status) {
        this.id = id;
        this.user = user;
        this.subscription = subscription;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
