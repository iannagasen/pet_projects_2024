package dev.agasen.productservice.command.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import dev.agasen.productservice.command.api.data.Product;
import dev.agasen.productservice.command.api.data.ProductRepository;

@Component
public class ProductEventHandler {

  private final ProductRepository productRepository;

  public ProductEventHandler(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @EventHandler
  public void on(ProductCreatedEvent event) {
    Product product = new Product();
    BeanUtils.copyProperties(event, product);
    productRepository.save(product);
  }
}
