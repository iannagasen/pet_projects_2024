package dev.agasen.cargo.booking.domain.model.valueobjects;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import dev.agasen.cargo.booking.domain.model.entities.Location;


@Embeddable
@Getter
@NoArgsConstructor
public class RouteSpecification {

  @Embedded
  @AttributeOverride(
    name="unLocCode", // name of java field to be overriden
    column=@Column(name="spec_origin_id") // override by what database column
  )
  private Location origin;

  @Embedded
  @AttributeOverride(
    name="unLocCode",
    column=@Column(name="spec_destination_id")
  )
  private Location destination;

  @Column(name="spec_arrival_deadline")
  @NotNull
  private LocalDateTime arrivalDeadline;


  /**
   * @param origin origin location - can't be the same as the destination
   * @param destination destination location - can't be the same as the origin
   * @param arrivalDeadline arrival deadline
   */
  public RouteSpecification(Location origin, Location destination, 
                            LocalDateTime arrivalDeadline) {
    this.origin = origin;
    this.destination = destination;
    this.arrivalDeadline = arrivalDeadline;
  }

}
