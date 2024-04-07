package dev.agasen.ecomm.application.usecases;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.agasen.ecomm.application.ports.input.ReviewInputPort;
import dev.agasen.ecomm.application.ports.output.ReviewOutputPort;
import dev.agasen.ecomm.domain.entity.Reviews;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewUseCase implements ReviewInputPort {

  private final ReviewOutputPort reviewOutputPort;

  @Override
  public Reviews getReviews(Long id) {
    return reviewOutputPort.getReviews(id);
  }

  @Override
  public Collection<Reviews> getAllReviews() {
    return reviewOutputPort.getAllReviews();
  }
  
}
