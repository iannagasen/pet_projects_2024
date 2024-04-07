package dev.agasen.ecomm.framework.adapters.output.mysql.data;

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
@Table(name = "image_paths")
@Entity(name = "image_paths")
public class ImagePathData extends BaseEntityAudit {
  
  private String path;

}
