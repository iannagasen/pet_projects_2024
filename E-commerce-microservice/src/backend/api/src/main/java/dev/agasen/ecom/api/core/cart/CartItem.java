package dev.agasen.ecom.api.core.cart;

import java.math.BigDecimal;

public record CartItem(
  Long productId,
  String productName,
  int quantity,
  BigDecimal price
) {
  
}
