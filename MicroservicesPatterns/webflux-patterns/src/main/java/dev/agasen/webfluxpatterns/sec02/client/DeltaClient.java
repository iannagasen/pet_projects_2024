package dev.agasen.webfluxpatterns.sec02.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec02.dto.FlightResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeltaClient implements Client {

  private final WebClient client;

  public DeltaClient(@Value("${sec02.delta.service}") String baseUrl) {
    this.client = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  @Override
  public Flux<FlightResult> getFlights(String from, String to) {
    return this.client
        .get()
        .uri("/{from}/{to}", from, to)
        .retrieve()
        .bodyToFlux(FlightResult.class)
        .onErrorResume(ex -> Mono.empty());
  }
}
