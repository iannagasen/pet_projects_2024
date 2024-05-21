package dev.agasen.webfluxpatterns.sec02.client;

import dev.agasen.webfluxpatterns.sec02.dto.FlightResult;
import reactor.core.publisher.Flux;

public interface Client {
  
  Flux<FlightResult> getFlights(String from, String to);
}
