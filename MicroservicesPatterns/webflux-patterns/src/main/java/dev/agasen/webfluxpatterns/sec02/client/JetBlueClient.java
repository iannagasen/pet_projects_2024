package dev.agasen.webfluxpatterns.sec02.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec02.dto.FlightResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JetBlueClient implements Client {
  
  private static final String JETBLUE = "JETBLUE";

  private final WebClient client;

  public JetBlueClient(@Value("${sec02.jetblue.service}") String baseUrl) {
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
        .map(unnormalizedResponse -> normalizeResponse(unnormalizedResponse, from, to))
        .onErrorResume(ex -> Mono.empty());
  }

  private FlightResult normalizeResponse(FlightResult result, String from, String to) {
    return new FlightResult(JETBLUE, from, to, result.price(), result.date());
  }
}
