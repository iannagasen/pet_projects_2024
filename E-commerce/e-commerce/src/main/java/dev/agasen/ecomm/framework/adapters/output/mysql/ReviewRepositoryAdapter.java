package dev.agasen.ecomm.framework.adapters.output.mysql;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.agasen.ecomm.application.ports.output.ReviewOutputPort;
import dev.agasen.ecomm.domain.entity.Reviews;
import dev.agasen.ecomm.framework.adapters.output.mysql.mappers.ToReviewEntity;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ReviewListRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryAdapter implements ReviewOutputPort {

  // private final ReviewRepository reviewRepository;
  private final ReviewListRepository reviewListRepository;

  @Override
  public Reviews getReviews(Long productId) {
    return reviewListRepository
        .findByProductId(productId)
        .map(r -> new Reviews(
          r.getProductId(), 
          r.getReviews().stream().map(new ToReviewEntity()).toList()
        ))
        .orElseThrow();
  }

  @Override
  public Collection<Reviews> getAllReviews() {
    return reviewListRepository.findAll().stream()
        .map(r -> new Reviews(
          r.getProductId(), 
          r.getReviews().stream().map(new ToReviewEntity()).toList()
        ))
        .toList(); 
  }
}