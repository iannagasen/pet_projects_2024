package dev.agasen.ecom.product_service.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

  @Id
  private String id;

  @Indexed(unique = true)
  private int productId;

  private String name;
  private String description;

  public ProductEntity(int productId, String name, String description) {
    this.productId = productId;
    this.name = name;
    this.description = description;
  }
  
}
