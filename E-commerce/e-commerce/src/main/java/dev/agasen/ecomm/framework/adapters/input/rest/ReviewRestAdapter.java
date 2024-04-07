package dev.agasen.ecomm.framework.adapters.input.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.ecomm.application.ports.input.ReviewInputPort;
import dev.agasen.ecomm.domain.entity.Reviews;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestAdapter {

  private final ReviewInputPort reviewInputPort;

  @GetMapping("/{id}")
  public Reviews getReviews(@PathVariable Long id) {
    return reviewInputPort.getReviews(id);
  }

  @GetMapping
  public Collection<Reviews> getAllReviews() {
    return reviewInputPort.getAllReviews();
  }
  
}
