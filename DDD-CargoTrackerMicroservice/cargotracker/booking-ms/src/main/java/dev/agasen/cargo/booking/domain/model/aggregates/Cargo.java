package dev.agasen.cargo.booking.domain.model.aggregates;

import org.springframework.data.domain.AbstractAggregateRoot;

import dev.agasen.cargo.booking.domain.model.commands.BookCargoCommand;
import dev.agasen.cargo.booking.domain.model.commands.RouteCargoCommand;
import dev.agasen.cargo.booking.domain.model.entities.Location;
import dev.agasen.cargo.booking.domain.model.valueobjects.BookingAmount;
import dev.agasen.cargo.booking.domain.model.valueobjects.CargoItinerary;
import dev.agasen.cargo.booking.domain.model.valueobjects.Delivery;
import dev.agasen.cargo.booking.domain.model.valueobjects.LastCargoHandledEvent;
import dev.agasen.cargo.booking.domain.model.valueobjects.RouteSpecification;
import dev.agasen.cargo.shareddomain.events.CargoBookedEvent;
import dev.agasen.cargo.shareddomain.events.CargoBookedEventData;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Aggregates
 *  - aggregate of any BContext should be able to express the business language of the BContext clearly
 *  - should not be anemic(pure getter/setter)
 * 
 * Important Domain Modeling Decision
 *  1. When a new cargo is booked, we will have:
 *    - a new Route Specification
 *    - an empty Cargo Itinerary
 *    - and no delivery progress
 *  2. As the cargo is assigned an itinerary
 *    - the empty itinerary is replaced by an allocated Cargo Itinerary
 *  3. As the cargo processes through multiple ports as part of its itinerary
 *    - the Delivery progress is updated and replaced within the root aggregate
 *  4. Finally, if the customer chooses to change the delivery
 *    - the Route Specfication changes, a new Cargo Itinerary will be assigned, 
 *      the delivery will be recalculated
 *      the Booking Amount changes
 */
@Entity
public class Cargo extends AbstractAggregateRoot<Cargo> {
  /**
   * ? Why use AbstractAggregateRoot
   */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Aggregate Identifier
   */
  @Embedded
  private BookingId bookingId;

  /**
   * The booking amount
   */
  @Embedded
  private BookingAmount bookingAmount;

  /**
   * Original Location of the Cargo
   */
  @Embedded
  private Location origin;

  /**
   * Route Specification of the cargo
   */
  @Embedded
  private RouteSpecification routeSpecification;

  /**
   * Itenerary assigned to the Cargo
   */
  @Embedded
  private CargoItinerary itinerary;

  /**
   * Checks the delivery progress of the cargo against the actual Route Specification and Itinerary
   */
  @Embedded
  private Delivery delivery;
  
  public Cargo() {
  }

  public Cargo(BookCargoCommand cmd) {
    this.bookingId = new BookingId(cmd.getBookingId());

    this.routeSpecification = new RouteSpecification(
      new Location(cmd.getOriginLocation()), 
      new Location(cmd.getDestLocation()), 
      cmd.getDestArrivalDeadline());

    this.origin = routeSpecification.getOrigin();

    // Empty itinerary since the Cargo has not been routed yet
    this.itinerary = CargoItinerary.EMPTY;

    this.bookingAmount = new BookingAmount(cmd.getBookingAmount());

    this.delivery = new Delivery(
      LastCargoHandledEvent.EMPTY, 
      this.itinerary, // also empty
      this.routeSpecification);

    //Add this domain event which needs to be fired when the new cargo is saved
    addDomainEvent(new CargoBookedEvent(new CargoBookedEventData(cmd.getBookingId())));
  }
  
  public void assignToRoute(RouteCargoCommand cmd) {
    this.itinerary = cmd.getCargoItinerary();
  
    // Handling consistency within the Cargo aggregate synchronously
    this.delivery = delivery.updateOnRouting(
      this.routeSpecification, 
      this.itinerary);

    // Add this domain event which needs to be fired when the new caargo is saved
    addDomainEvent(new CargoBookedEvent(new CargoBookedEventData(bookingId.getBookingId())));
  }

  public void addDomainEvent(Object event) {
    super.registerEvent(event);
  }
}
