package dev.agasen.webfluxpatterns.sec03.dto;

public record PaymentResponse(
  Integer userId,
  String name,
  Integer balance,
  Status status
) { 

}
