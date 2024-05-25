package dev.agasen.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.agasen.webfluxpatterns.sec03.dto.PaymentRequest;
import dev.agasen.webfluxpatterns.sec03.dto.PaymentResponse;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

  private final WebClient webClient;

  public UserClient(@Value("${sec03.user.service}") String baseUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public Mono<PaymentResponse> deductPayment(PaymentRequest request) {
    System.out.println("Calling ShippingClient::deductPayment");
    return callUserService("deduct", request);
  }

  public Mono<PaymentResponse> refund(PaymentRequest request) {
    System.out.println("Calling ShippingClient::refund");
    return callUserService("refund", request);
  }

  private Mono<PaymentResponse> callUserService(String endPoint, PaymentRequest request) {
    return webClient
        .post()
        .uri(endPoint)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(PaymentResponse.class)
        .onErrorReturn(buildErrorResponse(request));
  }

  private PaymentResponse buildErrorResponse(PaymentRequest request) {
    return new PaymentResponse(request.userId(), null, request.amount(), Status.FAILED);
  }
}
