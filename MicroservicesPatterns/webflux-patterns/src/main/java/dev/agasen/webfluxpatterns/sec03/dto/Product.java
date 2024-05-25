package dev.agasen.webfluxpatterns.sec03.dto;

public record Product(
  Integer productId,
  String category,
  String description,
  Integer price
) {
  
}
