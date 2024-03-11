package dev.agasen.cargo.booking.domain.valueobjects;

import java.time.LocalDateTime;

public record RouteSpecification(
  Location originLocation,
  Location destination,
  LocalDateTime arrivalDeadline
) {
  
}
