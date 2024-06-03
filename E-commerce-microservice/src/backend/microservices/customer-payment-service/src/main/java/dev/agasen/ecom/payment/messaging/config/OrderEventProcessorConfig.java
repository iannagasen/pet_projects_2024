package dev.agasen.ecom.payment.messaging.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import dev.agasen.ecom.api.event.order.OrderEvent;
import dev.agasen.ecom.api.event.payment.PaymentEvent;
import dev.agasen.ecom.api.processor.OrderEventProcessor;
import dev.agasen.ecom.api.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderEventProcessorConfig {

  private final OrderEventProcessor<PaymentEvent> eventProcessor;

  @Bean
  public Function<Flux<Message<OrderEvent>>, Flux<Message<PaymentEvent>>> processor() {
    return flux -> flux
        .map(MessageConverter::toRecord)
        .doOnNext(r -> log.info("Customer payment receivedP{}", r.message()))
        .concatMap(r -> this.eventProcessor
            .process(r.message())
            // * use doOnSuccess rather than doOnNext since Event.Completted will emit an empty mono
            .doOnSuccess(e -> r.acknowledgement().acknowledge())
        )
        .map(this::toMessage);
  }

  private Message<PaymentEvent> toMessage(PaymentEvent event) {
    return MessageBuilder.withPayload(event)
          .setHeader(KafkaHeaders.KEY, event.orderId().toString())
          .build();
  }
  
}
