package dev.agasen.ecom.api.processor;

import dev.agasen.ecom.api.event.DomainEvent;
import dev.agasen.ecom.api.event.shipping.ShippingEvent;
import dev.agasen.ecom.api.event.shipping.ShippingEvent.Scheduled;
import reactor.core.publisher.Mono;

public interface ShippingEventProcessor<R extends DomainEvent> extends EventProcessor<ShippingEvent, R>{

  @Override
  default Mono<R> process(ShippingEvent event) {
    return switch (event){
      case ShippingEvent.Scheduled e -> this.handle(e);
      default -> throw new IllegalArgumentException("would never happen");
    };
  }

  Mono<R> handle(Scheduled e);
  
}
