package dev.agasen.webfluxpatterns.sec01.dto;

public record ProductResponse(
  Integer id,
  String category,
  String description,
  Integer price
) {
  
}
