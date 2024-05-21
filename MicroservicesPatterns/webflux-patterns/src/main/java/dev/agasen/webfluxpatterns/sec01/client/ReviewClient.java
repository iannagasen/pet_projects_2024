package dev.agasen.webfluxpatterns.sec01.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec01.dto.Review;
import reactor.core.publisher.Mono;

@Service
public class ReviewClient {
    
  private final WebClient webClient;

  public ReviewClient(@Value("${sec01.review.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<List<Review>> getReviews(Integer id) {
    return this.webClient
        .get()
        .uri("{id}", id)
        .retrieve()
        .bodyToFlux(Review.class)
        .collectList(); // collectList is not actually blocking
  }

}
