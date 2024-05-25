package dev.agasen.webfluxpatterns.sec03.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {
  
  private final List<Orchestrator> orchestrators;

  public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx) {
    /**
     * ! Note: parallel theads that update the same object would give us some Raise Condition
     * ! but in this case, we are updating different fields in parallel, so it would be ok for now
     * ! but best practice is to not update it at the same time -- Maybe return a new object instead ? -- make it immutable, will it work ??
     */

    var orchestratorMonos = 
        orchestrators.stream()
                     .map(o -> o.create(ctx))
                     .collect(Collectors.toList());

    // What is `t -> t[0]`, you could change that to t[1], since it all our orchestratorMonos have the same value
    return Mono.zip(orchestratorMonos, t -> t[0])
        .cast(OrchestrationRequestContext.class)
        .doOnNext(this::updateStatus);
  }

  private void updateStatus(OrchestrationRequestContext ctx) {
    Status status = this.orchestrators.stream().allMatch(o -> o.isSuccess().test(ctx))
      ? Status.SUCCESS
      : Status.FAILED;

    ctx.setStatus(status);
  }
}
