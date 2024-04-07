package dev.agasen.ecomm;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ImagePathData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ProductData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.RatingData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewListData;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ProductRepository;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ReviewListRepository;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final ReviewRepository reviewRepository;
  private final ReviewListRepository reviewListRepository;

  @Override
  public void run(String... args) throws Exception {
    
    var product1 = new ProductData("Product1", "description of Product1", List.of(new ImagePathData("somePath")));
    var product2 = new ProductData("Product2", "description of Product2", List.of(new ImagePathData("somePath")));
    var product3 = new ProductData("Product3", "description of Product3", List.of(new ImagePathData("somePath")));

    // save 3 products
    List<ProductData> savedProducts = productRepository.saveAll(List.of(
      product1, product2, product3
    ));

    int i = 0;
    for (var savedProduct : savedProducts) {
      Long id = savedProduct.getId();

      int nextIndex = i++;
      var review1 = new ReviewData("tag " + nextIndex, "description " + nextIndex, new RatingData(4.5), List.of(new ImagePathData("somePath")));

      nextIndex = i++;
      var review2 = new ReviewData("tag " + nextIndex, "description " + nextIndex, new RatingData(4.5), List.of(new ImagePathData("somePath")));

      nextIndex = i++;
      var review3 = new ReviewData("tag " + nextIndex, "description " + nextIndex, new RatingData(4.5), List.of(new ImagePathData("somePath")));

      reviewListRepository.save(
        new ReviewListData(id, List.of(review1, review2, review3))
      );
    }
  }
  
}
