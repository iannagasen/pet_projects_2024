package dev.agasen.webfluxpatterns.sec02.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec02.dto.FlightResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FrontierClient implements Client {
  
  private final WebClient client;

  public FrontierClient(@Value("${sec02.frontier.service}") String baseUrl) {
    this.client = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  @Override
  public Flux<FlightResult> getFlights(String from, String to) {
    return this.client
        .post()
        .bodyValue(new FrontierRequest(from, to))
        .retrieve()
        .bodyToFlux(FlightResult.class)
        .onErrorResume(ex -> Mono.empty());
  }

  private record FrontierRequest(
    String from, 
    String to
  ) { }
}
