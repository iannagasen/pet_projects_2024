package dev.agasen.ecom.product_service.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
  
}