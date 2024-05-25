package dev.agasen.webfluxpatterns.sec03.service;

import java.util.function.Consumer;
import java.util.function.Predicate;

import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import reactor.core.publisher.Mono;

public interface Orchestrator {
  Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx);
  Predicate<OrchestrationRequestContext> isSuccess();
  Consumer<OrchestrationRequestContext> cancel();
}
