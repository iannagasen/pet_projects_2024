package dev.agasen.cargo.booking.domain.model.commands;

import java.time.LocalDateTime;

import dev.agasen.cargo.booking.domain.model.valueobjects.CargoItinerary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RouteCargoCommand {
  
  private String cargoBookingId;
  private String originLocation;
  private String destinationLocation;
  private LocalDateTime arrivalDeadline;
  private CargoItinerary cargoItinerary;

  public RouteCargoCommand(String originLocation, String destinationLocation, LocalDateTime arrivalDeadline,
      CargoItinerary cargoItinerary) {
    this.originLocation = originLocation;
    this.destinationLocation = destinationLocation;
    this.arrivalDeadline = arrivalDeadline;
    this.cargoItinerary = cargoItinerary;
  }

}
