package dev.agasen.webfluxpatterns.sec03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderCancellationService {
  
  private final List<Orchestrator> orchestrators;

  private Sinks.Many<OrchestrationRequestContext> sink;
  private Flux<OrchestrationRequestContext> flux;

  @PostConstruct
  public void init() {
    this.sink = Sinks.many().multicast().onBackpressureBuffer();
    this.flux = this.sink.asFlux().publishOn(Schedulers.boundedElastic());
    orchestrators.forEach(o -> this.flux.subscribe(o.cancel()));
  }

  public void cancelOrder(OrchestrationRequestContext ctx) {
    this.sink.tryEmitNext(ctx);
  }

}