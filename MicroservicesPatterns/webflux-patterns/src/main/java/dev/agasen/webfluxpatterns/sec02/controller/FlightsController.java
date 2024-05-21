package dev.agasen.webfluxpatterns.sec02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.webfluxpatterns.sec02.dto.FlightResult;
import dev.agasen.webfluxpatterns.sec02.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/sec02")
@RequiredArgsConstructor
public class FlightsController {
  
  private final FlightSearchService flightSearchService;

  @GetMapping(value = "/flights/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<FlightResult> getMethodName(@PathVariable String from, @PathVariable String to) {
    return this.flightSearchService.getFlights(from, to);
  }
  


}
