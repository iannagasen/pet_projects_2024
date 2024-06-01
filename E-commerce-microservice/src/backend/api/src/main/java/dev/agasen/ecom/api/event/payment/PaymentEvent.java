package dev.agasen.ecom.api.event.payment;

import java.time.Instant;

import dev.agasen.ecom.api.event.DomainEvent;
import dev.agasen.ecom.api.event.OrderSaga;
import lombok.Builder;

public interface PaymentEvent extends DomainEvent, OrderSaga {
  
  @Builder
  record Deducted(Long orderId,
                  Instant createdAt,
                  Long paymentId,
                  Long customerId,
                  int amount) implements PaymentEvent {};

  @Builder
  record Refunded(Long orderId,
                  Long paymentId,
                  Long customerId,
                  int amount,
                  Instant createdAt) implements PaymentEvent {};

  @Builder
  record Declined(Long orderId,
                  Long customerId,
                  String message,
                  int amount,
                  Instant createdAt) implements PaymentEvent {};
}
