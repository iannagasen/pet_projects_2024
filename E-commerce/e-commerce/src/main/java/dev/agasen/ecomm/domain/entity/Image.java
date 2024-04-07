package dev.agasen.ecomm.domain.entity;

public record Image (
  long id,
  String name,
  String type,
  byte[] imageData
) { }