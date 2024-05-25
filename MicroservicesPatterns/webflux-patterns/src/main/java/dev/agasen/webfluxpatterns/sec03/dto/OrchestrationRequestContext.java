package dev.agasen.webfluxpatterns.sec03.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrchestrationRequestContext {

  private final UUID orderId = UUID.randomUUID();
  private OrderRequest orderRequest;
  private Integer productPrice;
  private PaymentRequest paymentRequest;
  private PaymentResponse paymentResponse;
  private InventoryRequest inventoryRequest;
  private InventoryResponse inventoryResponse;
  private ShippingRequest shippingRequest;
  private ShippingResponse shippingRespons;
  private Status status;

  public OrchestrationRequestContext(OrderRequest request) {
    this.orderRequest = request;
  }
}