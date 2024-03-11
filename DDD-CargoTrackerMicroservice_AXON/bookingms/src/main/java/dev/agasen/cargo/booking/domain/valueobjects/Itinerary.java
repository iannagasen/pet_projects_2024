package dev.agasen.cargo.booking.domain.valueobjects;

import java.util.Collections;
import java.util.List;

public record Itinerary(List<Leg> legs) {
  public Itinerary() {
    this(Collections.emptyList());
  }
}
