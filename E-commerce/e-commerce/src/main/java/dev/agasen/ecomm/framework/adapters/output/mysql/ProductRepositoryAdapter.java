package dev.agasen.ecomm.framework.adapters.output.mysql;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.agasen.ecomm.application.ports.output.ProductOutputPort;
import dev.agasen.ecomm.domain.entity.Product;
import dev.agasen.ecomm.framework.adapters.output.mysql.mappers.ProductMapper;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductOutputPort {
  
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public Product getProductById(Long id) {
    return productRepository
        .findById(id)
        .map(productMapper::toDomainModel)
        .orElseThrow();
  }

  @Override
  public Collection<Product> getAll() {
    return productRepository
        .findAll()
        .stream()
        .map(productMapper::toDomainModel)
        .toList();
  }

}
