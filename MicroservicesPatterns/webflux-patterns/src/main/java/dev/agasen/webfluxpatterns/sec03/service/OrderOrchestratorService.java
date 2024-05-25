package dev.agasen.webfluxpatterns.sec03.service;

import org.springframework.stereotype.Service;

import dev.agasen.webfluxpatterns.sec03.client.ProductClient;
import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import dev.agasen.webfluxpatterns.sec03.dto.OrderRequest;
import dev.agasen.webfluxpatterns.sec03.dto.OrderResponse;
import dev.agasen.webfluxpatterns.sec03.dto.Product;
import dev.agasen.webfluxpatterns.sec03.dto.Status;
import dev.agasen.webfluxpatterns.sec03.util.DebugUtil;
import dev.agasen.webfluxpatterns.sec03.util.OrchestrationUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderOrchestratorService {
  
  private final ProductClient productClient;
  private final OrderFulfillmentService fulfillmentService;
  private final OrderCancellationService cancellationService;

  public Mono<OrderResponse> placeOrder(Mono<OrderRequest> order) {
    return order
      .map(OrchestrationRequestContext::new) 
      .flatMap(this::getProduct)
      .doOnNext(OrchestrationUtil::buildRequestContext)
      .flatMap(fulfillmentService::placeOrder)
      .doOnNext(this::doOrderPostProcessing)
      .doOnNext(DebugUtil::print)
      .map(this::toOrderResponse);
  }

  private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext ctx) {
    return this.productClient.getProduct(ctx.getOrderRequest().productId())
        .map(Product::price)
        .doOnNext(ctx::setProductPrice)
        // ! dont use this - since if the getProduct will return an empty mono,
        // ! the map() and doOnNext() will not execute
        // ! use map() instead, this is to emit empty mono, when getProduct emits an empty mono
        // .thenReturn(ctx);
        .map(i -> ctx);
  }

  private void doOrderPostProcessing(OrchestrationRequestContext ctx) {
    if (Status.FAILED.equals(ctx.getStatus())) {
      this.cancellationService.cancelOrder(ctx);
    }
  }

  private OrderResponse toOrderResponse(OrchestrationRequestContext ctx) {
    return new OrderResponse(
      ctx.getOrderRequest().userId(), 
      ctx.getOrderRequest().productId(), 
      ctx.getOrderId(), 
      ctx.getStatus(), 
      Status.SUCCESS.equals(ctx.getStatus()) ? ctx.getShippingRespons().address() : null,
      Status.SUCCESS.equals(ctx.getStatus()) ? ctx.getShippingRespons().expectedDelivery() : null      
    );
  }

}
