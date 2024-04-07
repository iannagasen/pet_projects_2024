package dev.agasen.ecomm.application.ports.input;

import java.util.Collection;

import dev.agasen.ecomm.domain.entity.Reviews;

public interface ReviewInputPort {

  Reviews getReviews(Long id);

  Collection<Reviews> getAllReviews();
  
}
