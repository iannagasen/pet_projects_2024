package dev.agasen.cargo.booking.domain.events;

import dev.agasen.cargo.booking.domain.valueobjects.BookingAmount;
import dev.agasen.cargo.booking.domain.valueobjects.Location;
import dev.agasen.cargo.booking.domain.valueobjects.RouteSpecification;

public record CargoBookedEvent(
  String bookingId,
  BookingAmount bookingAmount,
  Location originLocation,
  RouteSpecification routeSpecification
) { }
