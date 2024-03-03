package dev.agasen.cargo.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CargoRoutedEvent {
 
  private CargoRoutedEventData eventData;

}
