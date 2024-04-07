package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import java.util.List;

import dev.agasen.ecomm.framework.adapters.output.mysql.shared.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity(name = "product")
public class ProductData extends BaseEntityAudit {
  private String title;
  private String description;
  private List<String> imagePaths;  
}
