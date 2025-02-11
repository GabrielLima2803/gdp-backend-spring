package com.lima.GerenciamentoDePlanos.payment.application.controller;

import com.lima.GerenciamentoDePlanos.payment.application.dto.CreatePaymentDTO;
import com.lima.GerenciamentoDePlanos.payment.application.dto.PaymentResponseDTO;
import com.lima.GerenciamentoDePlanos.payment.application.usecases.PaymentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody @Valid CreatePaymentDTO request) {
        PaymentResponseDTO response = paymentUseCase.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> response = paymentUseCase.getAllPayments();
        return ResponseEntity.ok(response);
    }
}
