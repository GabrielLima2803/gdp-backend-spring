package com.lima.GerenciamentoDePlanos.payment.domain.exceptions;

public class PaymentValidationException extends RuntimeException {
    public PaymentValidationException(String message) {
        super(message);
    }
}
