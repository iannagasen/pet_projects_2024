package dev.agasen.ecomm.application.ports.input;

import java.util.Collection;

import dev.agasen.ecomm.domain.entity.Product;

public interface ProductInputPort {

  Product getProduct(Long id);

  Collection<Product> getAll();

}
