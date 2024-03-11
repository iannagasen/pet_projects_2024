package dev.agasen.cargo.booking.application.command.bookedevent;

import java.time.LocalDateTime;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import dev.agasen.cargo.booking.application.common.Command;
import dev.agasen.cargo.booking.domain.CargoAggregate;

public record BookCargoCommand(
  @TargetAggregateIdentifier
  String bookingId,
  int bookingAmount,
  String originLocation,
  String destLocation,
  LocalDateTime destArrivalDeadline
) implements Command<CargoAggregate> {
}
