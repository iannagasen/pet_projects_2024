package dev.agasen.ecomm.framework.adapters.output.mysql.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingSummaryData {
  
  private long rate;
  private long noOfRates;
  private long itemsSold;

}
