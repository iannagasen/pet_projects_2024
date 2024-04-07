package dev.agasen.ecomm.framework.adapters.input.rest;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.ecomm.application.ports.input.ProductInputPort;
import dev.agasen.ecomm.domain.entity.Product;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestAdapter {

  private final ProductInputPort ProductInputPort;

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable(name = "id") Long id) {
    return ProductInputPort.getProduct(id);
  }

  @GetMapping
  public Collection<Product> getAll() {
    return ProductInputPort.getAll();
  }
  
}
