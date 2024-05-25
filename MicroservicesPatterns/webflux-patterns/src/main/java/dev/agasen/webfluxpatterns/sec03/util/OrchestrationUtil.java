package dev.agasen.webfluxpatterns.sec03.util;

import dev.agasen.webfluxpatterns.sec03.dto.InventoryRequest;
import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import dev.agasen.webfluxpatterns.sec03.dto.PaymentRequest;
import dev.agasen.webfluxpatterns.sec03.dto.ShippingRequest;

public class OrchestrationUtil {
  
  public static void buildRequestContext(OrchestrationRequestContext ctx) {
    buildPaymentRequest(ctx);
    buildInventoryRequest(ctx);
    buildShippingRequest(ctx);
  }

  private static void buildPaymentRequest(OrchestrationRequestContext ctx) {
    var paymentRequest = new PaymentRequest(
        ctx.getOrderRequest().userId(),
        ctx.getProductPrice(), 
        ctx.getOrderId());

    ctx.setPaymentRequest(paymentRequest);
  }

  private static void buildInventoryRequest(OrchestrationRequestContext ctx) {
    var inventoryRequest = new InventoryRequest(
        ctx.getOrderId(), 
        ctx.getOrderRequest().productId(), 
        ctx.getOrderRequest().quantity());

    ctx.setInventoryRequest(inventoryRequest);
  }

  private static void buildShippingRequest(OrchestrationRequestContext ctx) {
    var shippingRequest = new ShippingRequest(
      ctx.getOrderRequest().quantity(), 
      ctx.getOrderRequest().userId(), 
      ctx.getOrderId());

    ctx.setShippingRequest(shippingRequest);
  }
}
