package dev.agasen.ecom.api.core.payment;

import reactor.core.publisher.Mono;

public interface CustomerPaymentService {
  
  Mono<CustomerPayment> process(PaymentProcessRequest request);

  Mono<CustomerPayment> refund(Long orderId);
  
}
