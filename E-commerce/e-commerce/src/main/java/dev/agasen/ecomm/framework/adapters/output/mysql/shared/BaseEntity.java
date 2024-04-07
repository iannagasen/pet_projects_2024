package dev.agasen.ecomm.framework.adapters.output.mysql.shared;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  
  @Id
  @GeneratedValue
  private Long id;

}
