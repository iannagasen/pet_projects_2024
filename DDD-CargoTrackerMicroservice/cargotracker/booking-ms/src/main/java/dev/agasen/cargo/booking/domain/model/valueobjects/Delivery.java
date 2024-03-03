package dev.agasen.cargo.booking.domain.model.valueobjects;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

import dev.agasen.cargo.booking.domain.model.entities.Location;

@Embeddable
public class Delivery {
  /**
   * nextExpectedActivity is not used
   */
  
  public static final LocalDateTime ETA_UNKNOWN = null;

  /**
   * Routing Status of the Cargo
   */
  @Enumerated(EnumType.STRING)
  @Column(name="routing_status")
  private RoutingStatus routingStatus;

  /**
   * Transport Status of the Cargo
   */
  @Enumerated(EnumType.STRING)
  @Column(name="transport_status")
  private TransportStatus transportStatus;

  /**
   * Previous information of the cargo. Helps the operator in determining the current state is OK.
   */
  @Column(name="last_known_location_id")
  @AttributeOverride(name="unLocCode", column=@Column(name="last_known_location_id"))
  private Location lastKnownLocation;

  /**
   * Current information of the cargo. Helps the operator in determining the current state is OK.
   */
  @Column(name="current_voyage_id")
  @AttributeOverride(name="voyageId", column=@Column(name="current_voyage_id"))
  private Voyage currentVoyage;

  @Embedded
  private LastCargoHandledEvent lastEvent;

  /**
   * Predictions for the Cargo activity. Helps the operator in determining if anything needs to be changed for the future.
   */
  @Embedded
  private CargoHandlingActivity nextExpectedActivity;


  public Delivery() {
  }

  public Delivery(LastCargoHandledEvent lastEvent, CargoItinerary itinerary,
                  RouteSpecification routeSpecification) {
    this.lastEvent = lastEvent;
    // TIMING is important here. example calculate first Transport Status, before current voyage.
    // TODO: maybe find a way to make the timing irrelevant here?
    this.routingStatus = calculateRoutingStatus(itinerary, routeSpecification);
    this.transportStatus = calculateTransportStatus();
    this.lastKnownLocation = calculateLastKnownLocation();
    this.currentVoyage = calculateCurrentVoyage();
  }

  public Delivery updateOnRouting(RouteSpecification routeSpecification, CargoItinerary cargoItinerary) {
    return new Delivery(this.lastEvent, cargoItinerary, routeSpecification);
  }  
  
  private Voyage calculateCurrentVoyage() {
    if (this.transportStatus.equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null) {
      return new Voyage(lastEvent.getHandlingEventVoyage());
    } else {
      return null;
    }
  }

  private Location calculateLastKnownLocation() {
    return lastEvent == null
        ? null
        : new Location(lastEvent.getHandlingEventLocation());
  }

  /**
   * Method to calculate the Routing status of a Cargo
   *
   * @param itinerary
   * @param routeSpecification
   * @return
   */
  private RoutingStatus calculateRoutingStatus(CargoItinerary itinerary, RouteSpecification routeSpecification) {
    return itinerary.isEmpty()
      ? RoutingStatus.NOT_ROUTED
      : RoutingStatus.ROUTED;
  }
  
  private TransportStatus calculateTransportStatus() {
    /**
     * ? Why are we not parameterizing the lastEvent here??
     */
    if (lastEvent.getHandlingEventType() == null) {
      return TransportStatus.NOT_RECEIVED;
    }

    switch (lastEvent.getHandlingEventType()) {
      case "LOAD": 
        return TransportStatus.ONBOARD_CARRIER;
      case "UNLOAD":
      case "RECIEVE":
      case "CUSTOMS":
        return TransportStatus.IN_PORT;
      case "CLAIM":
        return TransportStatus.CLAIMED;
      default:
        return TransportStatus.UNKNOWN;
    }
  }
}
