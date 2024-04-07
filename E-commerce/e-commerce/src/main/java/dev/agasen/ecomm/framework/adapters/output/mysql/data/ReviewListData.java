package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import java.util.ArrayList;
import java.util.List;

import dev.agasen.ecomm.framework.adapters.output.mysql.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
  name = "product_reviews",
  indexes = {
    @Index(name = "idx_product_id", columnList = "product_id")
  }
)
@Entity(name = "product_reviews")
public class ReviewListData extends BaseEntity {
  
  @Column(nullable = false)
  private Long productId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReviewData> reviews = new ArrayList<>();

}
