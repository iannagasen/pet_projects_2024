package dev.agasen.productservice.command.api.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.productservice.command.api.commands.CreateProductCommand;
import dev.agasen.productservice.command.api.model.ProductRestModel;

@RestController
@RequestMapping("/api/products")
public class ProductCommandController {

  private final CommandGateway commandGateway;

  public ProductCommandController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping
  public String addProduct(@RequestBody ProductRestModel productRestModel) {
    var command = CreateProductCommand.builder()
        .productId(UUID.randomUUID().toString())
        .name(productRestModel.getName())
        .price(productRestModel.getPrice())
        .quantity(productRestModel.getQuantity())
        .build();
        
    // send this command to a command gateway
    String result = commandGateway.sendAndWait(command);
    return result;
  }

}
