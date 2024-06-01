package dev.agasen.ecom.api.core.cart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {
  
  @GetMapping("/cart/{cartId}")
  Mono<Cart> getCart(@PathVariable Long cartId);

  @GetMapping("/cart")
  Flux<Cart> getCarts();
  
  
}