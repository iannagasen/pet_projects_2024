package dev.agasen.webfluxpatterns.sec01.dto;

import java.time.LocalDate;

public record PromotionResponse(
  Integer id,
  String type,
  Double discount,
  LocalDate endDate
) {
  
}
