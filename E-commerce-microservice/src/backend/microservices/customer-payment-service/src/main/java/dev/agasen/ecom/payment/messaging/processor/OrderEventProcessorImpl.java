package dev.agasen.ecom.payment.messaging.processor;

import java.util.function.UnaryOperator;

import org.springframework.stereotype.Component;

import dev.agasen.ecom.api.core.payment.CustomerPaymentService;
import dev.agasen.ecom.api.event.order.OrderEvent;
import dev.agasen.ecom.api.event.order.OrderEvent.Cancelled;
import dev.agasen.ecom.api.event.order.OrderEvent.Completted;
import dev.agasen.ecom.api.event.order.OrderEvent.Created;
import dev.agasen.ecom.api.event.payment.PaymentEvent;
import dev.agasen.ecom.api.exceptions.EventAlreadyProcessedException;
import dev.agasen.ecom.api.processor.OrderEventProcessor;
import dev.agasen.ecom.payment.messaging.mapper.CustomerPaymentMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProcessorImpl implements OrderEventProcessor<PaymentEvent>{

  private final CustomerPaymentService paymentService;
  private final CustomerPaymentMessageMapper paymentMsgMapper;

  @Override
  public Mono<PaymentEvent> handle(Created event) {
    return paymentService
        .process(paymentMsgMapper.toPaymentProcessRequest(event))
        .map(paymentMsgMapper::toPaymentDeductedEvent)
        .doOnNext(e -> log.info("payment processed: {}", e))
        .transform(filterAndHandleExceptions(event));
  }

  @Override
  public Mono<PaymentEvent> handle(Cancelled event) {
    return paymentService.refund(event.orderId())
        .map(this.paymentMsgMapper::toPaymentRefundedEvent)
        .doOnNext(e -> log.info("payment refunded: {}", e))
        .doOnError(ex -> log.error("error while processing refund", ex));
  }

  @Override
  public Mono<PaymentEvent> handle(Completted event) {
    return Mono.empty();
  }

  /**
   * * Will act like a filter for exceptions,
   * * if no exceptions thrown, will return the same mono
   */
  private UnaryOperator<Mono<PaymentEvent>> filterAndHandleExceptions(OrderEvent.Created e) {
    return mono -> mono
        // * if Event is already process, just return an empty mono (wc will eventually cancel the pipeline)
        .onErrorResume(EventAlreadyProcessedException.class, exc -> Mono.empty())
        // * for every other exception, map to Mono<PaymentEvent> with message = exception
        .onErrorResume(paymentMsgMapper.paymentDeclinedEventMapper(e));
  } 
}
