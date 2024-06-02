package dev.agasen.ecom.cart;

import org.springframework.web.bind.annotation.RestController;

import dev.agasen.ecom.api.core.cart.Cart;
import dev.agasen.ecom.api.core.cart.CartService;
import dev.agasen.ecom.cart.persistence.CartRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CartRestService implements CartService {

  private final CartRepository cartRepository;

  public @Override Mono<Cart> getCart(Long cartId) {
    return cartRepository
        .getCartByCartId(cartId)
        .map(cartRepository::toRestModel);
  }

  public @Override Flux<Cart> getCarts() {
    return cartRepository
        .findAll()
        .map(cartRepository::toRestModel);
  }
  
}
