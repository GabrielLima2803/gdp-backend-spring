package com.lima.GerenciamentoDePlanos.payment.application.usecases;

import com.lima.GerenciamentoDePlanos.payment.application.dto.CreatePaymentDTO;
import com.lima.GerenciamentoDePlanos.payment.application.dto.PaymentResponseDTO;
import com.lima.GerenciamentoDePlanos.payment.application.mappers.PaymentCreateAppMapper;
import com.lima.GerenciamentoDePlanos.payment.domain.enums.PaymentStatus;
import com.lima.GerenciamentoDePlanos.payment.domain.exceptions.PaymentValidationException;
import com.lima.GerenciamentoDePlanos.payment.domain.models.Payment;
import com.lima.GerenciamentoDePlanos.payment.domain.repositories.PaymentRepository;
import com.lima.GerenciamentoDePlanos.payment.infrastructure.persistence.mapper.PaymentPersistenceMapper;
import com.lima.GerenciamentoDePlanos.subscription.domain.exceptions.SubscriptionNotFoundException;
import com.lima.GerenciamentoDePlanos.subscription.domain.repositories.SubscriptionRepository;
import com.lima.GerenciamentoDePlanos.user.domain.exceptions.UserNotFoundException;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentCreateAppMapper paymentMapper;

    public PaymentUseCase(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository,
            PaymentCreateAppMapper paymentMapper
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponseDTO createPayment(CreatePaymentDTO paymentRequest) {
        if (paymentRequest.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentValidationException("Valor do pagamento deve ser positivo");
        }

        var user = userRepository.findById(paymentRequest.userId())
                .orElseThrow(() -> new UserNotFoundException(paymentRequest.userId()));

        var subscription = subscriptionRepository.findById(paymentRequest.subscriptionId())
                .orElseThrow(() -> new SubscriptionNotFoundException(paymentRequest.subscriptionId()));

        Payment newPayment = new Payment(
                null,
                user,
                subscription,
                paymentRequest.amount(),
                LocalDateTime.now(),
                PaymentStatus.PENDING
        );

        Payment savedPayment = paymentRepository.save(newPayment);
        return paymentMapper.toResponseDTO(savedPayment);
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}