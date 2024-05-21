package dev.agasen.webfluxpatterns.sec01.client;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec01.dto.PromotionResponse;
import reactor.core.publisher.Mono;

@Service
public class PromotionClient {

  public final static PromotionResponse NO_PROMOTION = new PromotionResponse(-1, "no promotion", 0.0, LocalDate.now());
  
  private final WebClient webClient;

  public PromotionClient(@Value("${sec01.promotion.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<PromotionResponse> getPromotion(Integer id) {
    return this.webClient
        .get()
        .uri("{id}", id)
        .retrieve()
        .bodyToMono(PromotionResponse.class);
  }

  
}
