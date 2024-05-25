package dev.agasen.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec03.dto.ShippingRequest;
import dev.agasen.webfluxpatterns.sec03.dto.ShippingResponse;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import reactor.core.publisher.Mono;

@Service
public class ShippingClient {

  private final WebClient webClient;

  public ShippingClient(@Value("${sec03.shipping.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<ShippingResponse> schedule(ShippingRequest request) {
    System.out.println("Calling ShippingClient::schedule");
    return callShippingService("schedule", request);
  }

  public Mono<ShippingResponse> cancel(ShippingRequest request) {
    System.out.println("Calling ShippingClient::cancel");
    return callShippingService("cancel", request);
  }

  private Mono<ShippingResponse> callShippingService(String endPoint, ShippingRequest request) {
    return webClient
        .post()
        .uri(endPoint)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(ShippingResponse.class)
        .onErrorReturn(buildErrorResponse(request));
  }

  private ShippingResponse buildErrorResponse(ShippingRequest request) {
    return new ShippingResponse(request.orderId(), request.quantity(), Status.FAILED, null, null);
  }
    
}
