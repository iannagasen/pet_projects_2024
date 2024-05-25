package dev.agasen.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec03.dto.InventoryRequest;
import dev.agasen.webfluxpatterns.sec03.dto.InventoryResponse;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {
  
  private final WebClient webClient;

  public InventoryClient(@Value("${sec03.inventory.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<InventoryResponse> deduct(InventoryRequest request) {
    System.out.println("Calling InventoryClient::deduct");
    return callInventoryService("deduct", request);
  }

  public Mono<InventoryResponse> restore(InventoryRequest request) {
    System.out.println("Calling InventoryClient::restore");
    return callInventoryService("restore", request);
  }

  private Mono<InventoryResponse> callInventoryService(String endPoint, InventoryRequest request) {
    return webClient
        .post()
        .uri(endPoint)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(InventoryResponse.class)
        .onErrorReturn(buildErrorResponse(request));
  }

  private InventoryResponse buildErrorResponse(InventoryRequest request) {
    return new InventoryResponse(request.productId(), request.quantity(), null, Status.FAILED);
  }
}
