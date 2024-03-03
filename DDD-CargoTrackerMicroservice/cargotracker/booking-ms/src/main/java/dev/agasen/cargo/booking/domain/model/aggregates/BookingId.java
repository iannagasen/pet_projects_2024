package dev.agasen.cargo.booking.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Business Key
 *  - a key that is exposed to external consumers of the Domain Model
 *  - a Technical Key on the other hand is a pure INTERNAL representation of 
 *    the aggregate identifier and is useful to maintain relationships 
 *    WITHIN A BOUNDED CONTEXT between the Aggregates and its Dependent Objects(Entities/VOs)
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingId {
  
  @Column
  private String bookingId;

}
