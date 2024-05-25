package dev.agasen.webfluxpatterns.sec03.service;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import dev.agasen.webfluxpatterns.sec03.client.UserClient;
import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentOrchestrator implements Orchestrator {

  private final UserClient userClient;

  @Override
  public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
    return this.userClient.deductPayment(ctx.getPaymentRequest())
        .doOnNext(ctx::setPaymentResponse)
        .thenReturn(ctx);
  }

  @Override
  public Predicate<OrchestrationRequestContext> isSuccess() {
    return ctx -> Status.SUCCESS.equals(ctx.getPaymentResponse().status());
  }

  @Override
  public Consumer<OrchestrationRequestContext> cancel() {
    // ! only cancel if isSuccess(), why?? bc. if it is not successful, payment is not created
    return ctx -> Mono.just(ctx)
        .filter(isSuccess())
        .map(OrchestrationRequestContext::getPaymentRequest)
        .flatMap(this.userClient::refund)
        .subscribe();
  }
  
}
