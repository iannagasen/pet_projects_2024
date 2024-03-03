package dev.agasen.cargo.booking.domain.model.valueobjects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Collections;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CargoItinerary {
  
  public static final CargoItinerary EMPTY = new CargoItinerary();

  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="cargo_id")
  @OrderBy("loadTime")
  private List<Leg> legs = Collections.emptyList();

  public List<Leg> getLegs() {
    return Collections.unmodifiableList(legs);
  }

  public boolean isEmpty() {
    return this == null || this == CargoItinerary.EMPTY;
  }

}
