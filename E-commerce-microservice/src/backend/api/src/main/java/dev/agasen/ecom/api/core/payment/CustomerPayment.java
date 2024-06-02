package dev.agasen.ecom.api.core.payment;

import dev.agasen.ecom.api.event.payment.PaymentStatus;
import lombok.Builder;

@Builder
public record CustomerPayment(String id, Long orderId, Long customerId, PaymentStatus status, Long amount) {}
