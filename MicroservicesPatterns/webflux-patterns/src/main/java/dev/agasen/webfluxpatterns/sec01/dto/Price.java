package dev.agasen.webfluxpatterns.sec01.dto;

public record Price(
  Integer listPrice,
  Double discount,
  Double discountedPrice,
  Double amountSaved
) {
  
}
