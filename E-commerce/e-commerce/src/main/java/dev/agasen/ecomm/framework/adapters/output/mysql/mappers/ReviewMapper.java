package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.agasen.ecomm.domain.entity.Rating;
import dev.agasen.ecomm.domain.entity.Review;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.RatingData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewData;

@Component
public class ReviewMapper implements BaseMapper<ReviewData, Review>{

  @Override
  public ReviewData toDataModel(Review domain) {
    throw new UnsupportedOperationException("Unimplemented method 'toDataModel'");
  }

  @Override
  public Review toDomainModel(ReviewData t) {
    return new Review(
      t.getId(), 
      t.getUpdatedAt(),
      toRatingEntity(t.getRating()),
      "user -- TBD", 
      List.of("somePath -- TBD"));
  }

  private Rating toRatingEntity(RatingData rating) {
    return new Rating(rating.getRate());
  }
  
}
