package dev.agasen.ecom.api.core.inventory;

public record Inventory(
  Long productId,
  int stock
) {
}