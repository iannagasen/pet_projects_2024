package dev.agasen.ecom.api.core.payment;

public record PaymentProcessRequest(Long customerId, Long orderId, Long amount) {

}
