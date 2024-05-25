package dev.agasen.webfluxpatterns.sec03.dto;

public record ProductResponse(
  Integer id,
  String category,
  String description,
  Integer price
) { }
