package dev.agasen.webfluxpatterns.sec01.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.agasen.webfluxpatterns.sec01.client.ProductClient;
import dev.agasen.webfluxpatterns.sec01.client.PromotionClient;
import dev.agasen.webfluxpatterns.sec01.client.ReviewClient;
import dev.agasen.webfluxpatterns.sec01.dto.Price;
import dev.agasen.webfluxpatterns.sec01.dto.ProductAggregate;
import dev.agasen.webfluxpatterns.sec01.dto.ProductResponse;
import dev.agasen.webfluxpatterns.sec01.dto.PromotionResponse;
import dev.agasen.webfluxpatterns.sec01.dto.Review;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductAggregatorService {
  
  private final ProductClient productClient;
  private final PromotionClient promotionClient;
  private final ReviewClient reviewClient;

  public Mono<ProductAggregate> aggregate(Integer id){
    /** 
      * ! using Mono.zip() is all or nothing  -- makes it not resilient when 1 fail all fails
      * ? add default on error values
      * 
      * ! What if the service is totally down
      * ? same solution with the adding default error values
      * 
      * ! what if the product service is down??? this is a core service
      * ? add `.onErrorResume(ex -> Mono.empty())` this will be handled by the caller in controller `.defaultIfEmpty(NOT_FOUND)`
      */
    return Mono.zip(
      productClient.getProduct(id).onErrorResume(ex -> Mono.empty()),
      promotionClient.getPromotion(id).onErrorReturn(PromotionClient.NO_PROMOTION),
      reviewClient.getReviews(id).onErrorReturn(Collections.emptyList())
    )
    .map(t -> toDto(t.getT1(), t.getT2(), t.getT3()));
  }

  private ProductAggregate toDto(ProductResponse product, PromotionResponse promotion, List<Review> reviews) {
    var amountSaved = product.price() * promotion.discount() / 100;
    var discounted = product.price() - amountSaved;
    return new ProductAggregate(
      product.id(), 
      product.category(), 
      product.description(), 
      new Price(product.price(), promotion.discount(), discounted, amountSaved),
      reviews);
  }
}
