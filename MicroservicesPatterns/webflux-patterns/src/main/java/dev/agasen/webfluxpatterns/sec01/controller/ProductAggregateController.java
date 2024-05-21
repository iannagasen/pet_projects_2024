package dev.agasen.webfluxpatterns.sec01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.webfluxpatterns.sec01.dto.ProductAggregate;
import dev.agasen.webfluxpatterns.sec01.service.ProductAggregatorService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("sec01")
@RequiredArgsConstructor
public class ProductAggregateController {
 
  private final ProductAggregatorService service;

  @GetMapping("product/{id}")
  public Mono<ResponseEntity<ProductAggregate>> getProductAggregate(@PathVariable Integer id) {
    return this.service.aggregate(id)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  
}
