package dev.agasen.ecom.cart_service.persistence;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.agasen.ecom.api.core.cart.CartItem;
import lombok.Data;

@Data
@Document(collection="cart")
public class CartEntity {
  
  private @Id String id;
  private @Indexed(unique=true) Long cartId;
  private List<CartItem> items;
  
}
