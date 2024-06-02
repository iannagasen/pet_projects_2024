package dev.agasen.ecom.inventory_service.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import dev.agasen.ecom.api.core.inventory.Inventory;
import dev.agasen.ecom.api.core.inventory.InventoryUpdate;
import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveMongoRepository<InventoryEntity, String> {

  Mono<InventoryEntity> findByProductId(Long productId);
  
  default Inventory toInventoryRestModel(InventoryEntity e, List<InventoryUpdate> history) {
    return new Inventory(e.getProductId(), e.getStock(), history);
  }
  
}