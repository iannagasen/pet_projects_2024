package dev.agasen.ecom.api.core.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;

public interface InventoryService {

  @GetMapping("/inventory/{productId}")
  Mono<Inventory> getInventory(@PathVariable Long productId);
  
}
