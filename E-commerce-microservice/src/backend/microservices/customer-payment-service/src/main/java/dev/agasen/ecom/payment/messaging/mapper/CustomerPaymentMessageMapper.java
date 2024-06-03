package dev.agasen.ecom.payment.messaging.mapper;

import java.time.Instant;
import java.util.function.Function;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;
import org.springframework.stereotype.Component;

import dev.agasen.ecom.api.core.payment.CustomerPayment;
import dev.agasen.ecom.api.core.payment.PaymentProcessRequest;
import dev.agasen.ecom.api.event.order.OrderEvent;
import dev.agasen.ecom.api.event.payment.PaymentEvent;
import reactor.core.publisher.Mono;

@Component
public class CustomerPaymentMessageMapper {
  
  public PaymentProcessRequest toPaymentProcessRequest(OrderEvent.Created event) {
    return PaymentProcessRequest.builder()
        .customerId(event.customerId())
        .orderId(event.orderId())
        .amount(event.totalAmount())
        .build();
  }

  public PaymentEvent toPaymentDeductedEvent(CustomerPayment p) {
    return PaymentEvent.Deducted.builder()
        .paymentId(p.id())
        .orderId(p.orderId())
        .amount(p.amount())
        .customerId(p.customerId())
        .createdAt(Instant.now())
        .build();
  }

  public Function<Throwable, Mono<PaymentEvent>> paymentDeclinedEventMapper(OrderEvent.Created e) {
    return exc -> Mono.fromSupplier(
        () -> PaymentEvent.Declined.builder()
            .orderId(e.orderId())
            .amount(e.totalAmount())
            .customerId(e.customerId())
            .createdAt(Instant.now())
            .message(exc.getMessage())
            .build()
    );
  }

  public PaymentEvent toPaymentRefundedEvent(CustomerPayment p) {
    return PaymentEvent.Refunded.builder()
        .paymentId(p.id())
        .orderId(p.orderId())
        .amount(p.amount())
        .customerId(p.customerId())
        .createdAt(Instant.now())
        .build();
  }

}
