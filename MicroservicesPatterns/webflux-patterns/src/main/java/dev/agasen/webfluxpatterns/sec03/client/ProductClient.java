package dev.agasen.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec03.dto.Product;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
  
  private final WebClient webClient;

  public ProductClient(@Value("${sec03.product.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<Product> getProduct(Integer id) {
    return this.webClient
        .get()
        .uri("{id}", id)
        .retrieve()
        .bodyToMono(Product.class)
        // ! since this is a the base microservice client,
        // ! when this client call have an error, just transform it to an empty
        .onErrorResume(ex -> Mono.empty());
  }
}