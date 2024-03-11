package dev.agasen.cargo.booking.application.command.bookedevent;

import org.springframework.stereotype.Component;

import dev.agasen.cargo.booking.application.common.BaseCommandHandler;
import dev.agasen.cargo.booking.domain.CargoAggregate;


@Component
public class BookCargoCommandHandler extends BaseCommandHandler<CargoAggregate, BookCargoCommand> {

  @Override
  public CargoAggregate handle(BookCargoCommand command) {
    CargoAggregate aggregate = CargoAggregate.withBooking(
      command.bookingId(), 
      command.bookingAmount(), 
      command.originLocation(), 
      command.destLocation(), 
      command.destArrivalDeadline());
    return aggregate;
  }

}
