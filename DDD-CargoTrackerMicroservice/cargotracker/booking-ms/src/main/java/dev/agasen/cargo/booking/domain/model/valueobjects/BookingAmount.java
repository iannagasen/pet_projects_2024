package dev.agasen.cargo.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Domain model representation of the Booking Amount for a new Cargo
 * Contains the Booking Amount of the Cargo
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingAmount {
  
  @Column(
    name="booking_amount",
    updatable=false) // remember value objects are immutable
  private Integer bookingAmount;

} 
