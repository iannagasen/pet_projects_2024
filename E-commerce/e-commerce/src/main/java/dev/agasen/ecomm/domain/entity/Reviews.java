package dev.agasen.ecomm.domain.entity;

import java.util.List;

public record Reviews(
  Long productId,
  List<Review> reviews
) {

}