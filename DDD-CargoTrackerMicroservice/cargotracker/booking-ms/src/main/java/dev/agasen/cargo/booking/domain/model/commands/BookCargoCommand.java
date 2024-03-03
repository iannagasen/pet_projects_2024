package dev.agasen.cargo.booking.domain.model.commands;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookCargoCommand {
  
  private String bookingId;
  private int bookingAmount;
  private String originLocation;
  private String destLocation;
  private LocalDateTime destArrivalDeadline;


  public BookCargoCommand(int bookingAmount, String originLocation, String destLocation,
      LocalDateTime destArrivalDeadline) {
    this.bookingAmount = bookingAmount;
    this.originLocation = originLocation;
    this.destLocation = destLocation;
    this.destArrivalDeadline = destArrivalDeadline;
  }
  
}
