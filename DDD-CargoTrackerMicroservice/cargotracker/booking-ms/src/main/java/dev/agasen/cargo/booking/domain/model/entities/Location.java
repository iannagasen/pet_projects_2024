package dev.agasen.cargo.booking.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Location class represented by a unique 5 digit UN Location Code
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
  
  /**
   * 5 digit Location Code
   */
  @Column(
    name="origin_id",
    insertable=false,
    updatable=false
  )
  private String unLocCode;
}
