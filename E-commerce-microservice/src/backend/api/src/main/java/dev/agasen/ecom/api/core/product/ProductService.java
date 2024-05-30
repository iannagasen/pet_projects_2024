package dev.agasen.ecom.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  @GetMapping("/products")
  Flux<Product> getProducts();
  
  @GetMapping("/products/{id}")
  Mono<Product> getProduct(@PathVariable Long id);

  @GetMapping("/products/category/{category}")
  Flux<Product> getProductsByCategory(@PathVariable String category);

}
