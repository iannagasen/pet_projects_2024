package dev.agasen.ecomm.application.ports.output;

import java.util.Collection;

import dev.agasen.ecomm.domain.entity.Product;

public interface ProductOutputPort {

  Product getProductById(Long id);

  Collection<Product> getAll();
  
}
