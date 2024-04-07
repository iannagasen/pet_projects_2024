package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import dev.agasen.ecomm.framework.adapters.output.mysql.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "image_data")
@Table(name = "image_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageData extends BaseEntity {

  private String name;
  private String type;

  @Lob
  @Column(length = 100000)
  private byte[] imageData;
}