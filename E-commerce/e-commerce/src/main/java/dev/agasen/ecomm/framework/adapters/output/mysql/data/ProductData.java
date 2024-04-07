package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import java.util.ArrayList;
import java.util.List;

import dev.agasen.ecomm.framework.adapters.output.mysql.shared.BaseEntityAudit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ImagePathData> imagePaths = new ArrayList<>();
}
