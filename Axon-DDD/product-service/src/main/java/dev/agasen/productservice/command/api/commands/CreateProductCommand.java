package dev.agasen.productservice.command.api.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCommand {
  /**
   * Should store the state/payload of what we want to do
   * 
   * should have an identifier
   */

  @TargetAggregateIdentifier
  private String productId;
  
  private String name;
  private BigDecimal price;
  private Integer quantity;
}
