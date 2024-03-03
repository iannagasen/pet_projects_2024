package dev.agasen.cargo.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LastCargoHandledEvent {
  
  // null object pattern
  public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();

  @Column(name="last_handling_event_id")
  private Integer handlingEventId;

  @Column(name="last_handling_event_type")
  private String handlingEventType;

  @Column(name="last_handling_event_voyage")
  private String handlingEventVoyage;

  @Column(name="last_handling_event_location")
  private String handlingEventLocation;



}
