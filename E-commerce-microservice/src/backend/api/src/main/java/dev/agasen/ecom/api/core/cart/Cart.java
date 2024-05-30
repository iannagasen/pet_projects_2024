package dev.agasen.ecom.api.core.cart;

import java.util.List;

public record Cart(
  Long userId,
  List<CartItem> items
) { }