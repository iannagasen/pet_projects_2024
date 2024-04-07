package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

import dev.agasen.ecomm.domain.entity.Rating;
import dev.agasen.ecomm.domain.entity.Review;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.RatingData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewData;
import java.util.function.Function;
import java.util.List;

public class ToReviewEntity implements Function<ReviewData, Review>{

  @Override
  public Review apply(ReviewData t) {
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
