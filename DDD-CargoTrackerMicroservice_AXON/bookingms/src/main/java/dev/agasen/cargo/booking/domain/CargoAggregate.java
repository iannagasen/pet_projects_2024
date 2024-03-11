package dev.agasen.cargo.booking.domain;

import java.time.LocalDateTime;

import org.axonframework.lifecycle.Lifecycle.LifecycleHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import ch.qos.logback.core.spi.LifeCycle;
import dev.agasen.cargo.booking.domain.enums.RoutingStatus;
import dev.agasen.cargo.booking.domain.enums.TransportStatus;
import dev.agasen.cargo.booking.domain.events.CargoBookedEvent;
import dev.agasen.cargo.booking.domain.valueobjects.BookingAmount;
import dev.agasen.cargo.booking.domain.valueobjects.Itinerary;
import dev.agasen.cargo.booking.domain.valueobjects.Location;
import dev.agasen.cargo.booking.domain.valueobjects.RouteSpecification;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@Slf4j
public class CargoAggregate {

  @AggregateIdentifier
  private String bookingId;

  private BookingAmount bookingAmount; 
  private Location origin;
  private RouteSpecification routeSpecification;
  private Itinerary itinerary;
  private RoutingStatus routingStatus; 
  private TransportStatus transportStatus;

  // protected CargoAggregate(String bookingId, BookingAmount bookingAmount, Location origin,
  //       RouteSpecification routeSpecification, Itinerary itinerary, 
  //       RoutingStatus routingStatus, TransportStatus transportStatus) 
  // {
  //   this.bookingId = bookingId;
  //   this.bookingAmount = bookingAmount;
  //   this.origin = origin;
  //   this.routeSpecification = routeSpecification;
  //   this.itinerary = itinerary;
  //   this.routingStatus = routingStatus;
  //   this.transportStatus = transportStatus;
  // }

  public CargoAggregate () {
    log.info("Empty Cargo created.");
  }

  public static CargoAggregate withBooking(String bookingId, int bookingAmount, 
       String originLocation, String destLocation, LocalDateTime destArrivalDeadline) 
  {
    CargoAggregate emptyAggregate = new CargoAggregate();

    if (bookingAmount < 0) {
      throw new IllegalArgumentException("Booking ammounnt cannot be negative");
    }

    var bookingAmt = new BookingAmount(bookingAmount);
    var originLoc = new Location(originLocation);
    var destLoc = new Location(destLocation);
    var initialRouteSpecs = new RouteSpecification(originLoc, destLoc, destArrivalDeadline);
    
    CargoBookedEvent cargoBookedEvent = new CargoBookedEvent(bookingId, bookingAmt, originLoc, initialRouteSpecs);
    AggregateLifecycle.apply(cargoBookedEvent);

    return emptyAggregate;
  }

  

  


}