package dev.agasen.ecomm;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ProductData;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements CommandLineRunner {

  private final ProductRepository productRepository;

  @Override
  public void run(String... args) throws Exception {
    var product1 = new ProductData("Product1", "description of Product1", List.of("/somepath"));
    var product2 = new ProductData("Product2", "description of Product2", List.of("/somepath"));
    var product3 = new ProductData("Product3", "description of Product3", List.of("/somepath"));

    productRepository.saveAll(List.of(
      product1, product2, product3
    ));

  }
  
}
