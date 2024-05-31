package dev.agasen.ecom.api.core.inventory;

import java.time.LocalDateTime;

public record InventoryUpdate(
  Long productId,
  InventoryUpdateType type,
  LocalDateTime dateTime
) { }