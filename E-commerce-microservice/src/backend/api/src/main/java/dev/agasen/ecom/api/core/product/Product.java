package dev.agasen.ecom.api.core.product;

public record Product(
  Long id,
  String name, 
  String description
) { }