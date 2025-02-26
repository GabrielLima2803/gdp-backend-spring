package com.lima.GerenciamentoDePlanos.payment.application.usecases;

import com.lima.GerenciamentoDePlanos.notification.application.dtos.NotificationEvent;
import com.lima.GerenciamentoDePlanos.notification.infrastructure.persistence.messaging.NotificationProducer;
import com.lima.GerenciamentoDePlanos.payment.application.dto.CreatePaymentDTO;
import com.lima.GerenciamentoDePlanos.payment.application.dto.PaymentDTO;
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
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentCreateAppMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public PaymentUseCase(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository,
            PaymentCreateAppMapper paymentMapper,
            NotificationProducer notificationProducer
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentMapper = paymentMapper;
        this.notificationProducer = notificationProducer;
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

    public PaymentResponseDTO payPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new RuntimeException("Pagamento ja foi realizado");
        }

        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        User user = savedPayment.getUser();
        user.setSelectSubscription(savedPayment.getSubscription());
        userRepository.save(user);

        NotificationEvent event = new NotificationEvent(
                user.getId(),
                "Pagamento realizado com sucesso",
                "Seu pagamento de R$ " + savedPayment.getAmount() + " foi processado com sucesso."
        );
        notificationProducer.sendNotification(event);
        return paymentMapper.toResponseDTO(savedPayment);
    }
    public List<PaymentDTO> getPaymentHistory() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = jwt.getSubject();
        log.info("User email: {}", userEmail);
        List<Payment> payments = paymentRepository.findByUserEmail(userEmail);
        return payments.stream()
                .map(PaymentDTO::fromEntity)
                .collect(Collectors.toList());
    }

}