package dev.agasen.ecomm.application.usecases;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.agasen.ecomm.application.ports.input.ProductInputPort;
import dev.agasen.ecomm.application.ports.output.ProductOutputPort;
import dev.agasen.ecomm.domain.entity.Product;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductInputPort {

  private final ProductOutputPort productOutputPort;

  @Override
  public Product getProduct(Long id) {
    return productOutputPort.getProductById(id);
  }

  @Override
  public Collection<Product> getAll() {
    return productOutputPort.getAll();
  }
  
}
