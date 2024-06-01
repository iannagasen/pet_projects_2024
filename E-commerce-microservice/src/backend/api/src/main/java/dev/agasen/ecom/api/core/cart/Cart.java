package dev.agasen.ecom.api.core.cart;

import java.util.List;

import lombok.Builder;

@Builder
public record Cart(
  Long cartId,
  Long userId,
  List<CartItem> items
) { }