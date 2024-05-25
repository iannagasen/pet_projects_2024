package dev.agasen.webfluxpatterns.sec03.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.webfluxpatterns.sec03.dto.OrderRequest;
import dev.agasen.webfluxpatterns.sec03.dto.OrderResponse;
import dev.agasen.webfluxpatterns.sec03.service.OrderOrchestratorService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sec03")
@RequiredArgsConstructor
public class OrderController {
  
  private final OrderOrchestratorService service;

  @PostMapping("/order")
  public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> mono) {
    return this.service.placeOrder(mono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  } 

}
