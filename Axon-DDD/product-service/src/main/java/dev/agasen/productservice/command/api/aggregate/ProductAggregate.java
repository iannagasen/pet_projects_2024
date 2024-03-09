package dev.agasen.productservice.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import dev.agasen.productservice.command.api.commands.CreateProductCommand;
import dev.agasen.productservice.command.api.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
  /**
   * Similar to Command,
   *  BUT the identifier is @AggregateIdentifier rather than @TargetAggregateIdentifier
   *  ? but why create a separate class for this
   * 
   * add an EventSourcingHandler on every Aggregate:
   *  ! this is to update the state or snapshot of the Aggregate
   * 
   */

  @AggregateIdentifier
  private String productId;
  private String name;
  private BigDecimal price;
  private Integer quantity;

  public ProductAggregate() {
  }

  @CommandHandler
  public ProductAggregate(CreateProductCommand command) {
    /**
     * 1. PERFORM ALL VALIDATIONS HERE
     * 2. CREATE THE EVENT
     * 3. PUBLISH THE EVENT
     */

    var event = new ProductCreatedEvent();
    BeanUtils.copyProperties(command, event);

    // Publish the event to the Event Store
    // Whenever you publish the event, you need to update the state of the aggregate
    AggregateLifecycle.apply(event);
  }


  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    /**
     * to update the state or snapshot of this aggregate
     */
    this.productId = productCreatedEvent.getProductId();
    this.name = productCreatedEvent.getName();
    this.price = productCreatedEvent.getPrice();
    this.quantity = productCreatedEvent.getQuantity();
  }
}
