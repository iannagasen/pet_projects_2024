package dev.agasen.cargo.booking.domain.model.valueobjects;

import dev.agasen.cargo.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Leg {
  
  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private Voyage voyage;

  @Embedded
  @AttributeOverride(name="unLocCode", column=@Column(name="load_location_id"))
  private Location loadLocation;
  
  @Embedded
  @AttributeOverride(name="unLocCode", column=@Column(name="unload_location_id"))
  private Location unloadLocation;

  @NotNull
  private LocalDateTime loadTime;

  @NotNull
  private LocalDateTime unloadTime;

  public Leg(Voyage voyage, Location loadLocation, Location unloadLocation, 
             LocalDateTime loadTime, LocalDateTime unloadTime) {
    this.voyage = voyage;
    this.loadLocation = loadLocation;
    this.unloadLocation = unloadLocation;
    this.loadTime = loadTime;
    this.unloadTime = unloadTime;
  }

  
}
