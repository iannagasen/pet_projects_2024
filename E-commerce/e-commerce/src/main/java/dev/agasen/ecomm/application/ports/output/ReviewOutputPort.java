package dev.agasen.ecomm.application.ports.output;

import java.util.Collection;

import dev.agasen.ecomm.domain.entity.Reviews;

public interface ReviewOutputPort {

  Reviews getReviews(Long productId);
  
  Collection<Reviews> getAllReviews();
  
}
