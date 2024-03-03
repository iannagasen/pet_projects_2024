package dev.agasen.cargo.booking.domain.model.valueobjects;

import dev.agasen.cargo.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
@Getter
public class CargoHandlingActivity {

  public static final CargoHandlingActivity NO_ACTIVITY = new CargoHandlingActivity();
  
  @Column(name="next_expected_handling_event_type")
  private String type;

  @Column(name="next_expected_location_id")
  @Embedded
  @AttributeOverride(name="unLocCode", column=@Column(name="next_expected_location_id"))
  private Location location;

  @Column(name="next_expected_voyage_id")
  @Embedded
  @AttributeOverride(name="voyageId", column=@Column(name="next_expected_voyage_id"))
  private Voyage voyage;

  public CargoHandlingActivity() {
  }

  public CargoHandlingActivity(Location location, Voyage voyage) {
    this.location = location;
    this.voyage = voyage;
  }

  public CargoHandlingActivity(String type, Location location, Voyage voyage) {
    this.type = type;
    this.location = location;
    this.voyage = voyage;
  }

}
