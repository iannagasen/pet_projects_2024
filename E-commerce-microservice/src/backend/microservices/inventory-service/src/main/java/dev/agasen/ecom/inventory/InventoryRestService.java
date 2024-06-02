package dev.agasen.ecom.inventory;

import org.springframework.web.bind.annotation.RestController;

import dev.agasen.ecom.api.core.inventory.Inventory;
import dev.agasen.ecom.api.core.inventory.InventoryService;
import dev.agasen.ecom.api.core.inventory.InventoryUpdate;
import dev.agasen.ecom.inventory.persistence.InventoryRepository;
import dev.agasen.ecom.inventory.persistence.InventoryUpdateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class InventoryRestService implements InventoryService {

  private final InventoryRepository inventoryRepo;
  private final InventoryUpdateRepository inventoryHistoryRepo;
  
  public @Override Mono<Inventory> getInventory(Long productId) {
    return inventoryRepo
      .findByProductId(productId)
      .map(i -> inventoryRepo.toInventoryRestModel(i, null));
  }

  public @Override Flux<Inventory> getInventories() {
    return inventoryRepo
      .findAll()
      .map(i -> inventoryRepo.toInventoryRestModel(i, null));
  }

  public @Override Flux<InventoryUpdate> getInventoryHistory(Long productId) {
    return inventoryHistoryRepo
      .findAllByProductId(productId)
      .map(inventoryHistoryRepo::toRestModel);
  }
  
}