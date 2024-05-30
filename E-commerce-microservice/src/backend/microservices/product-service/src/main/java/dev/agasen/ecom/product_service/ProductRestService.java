package dev.agasen.ecom.product_service;

import org.springframework.web.bind.annotation.RestController;

import dev.agasen.ecom.api.core.product.Product;
import dev.agasen.ecom.api.core.product.ProductService;
import dev.agasen.ecom.product_service.persistence.ProductEntity;
import dev.agasen.ecom.product_service.persistence.ProductRepository;
import dev.agasen.ecom.util.mongo.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductRestService implements ProductService {

  private final ProductRepository repository;
  private final SequenceGeneratorService sequence;

  public @Override Flux<Product> getProducts() {
    return repository.findAll().map(this::toProductRestModel);
  }
  
  public @Override Mono<Product> getProduct(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'getProduct'");
  }
  
  public @Override Flux<Product> getProductsByCategory(String category) {
    throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategory'");
  }
  
  public @Override Mono<Product> createProduct(Product product) {
    return sequence.generateSequence(ProductEntity.SEQUENCE_NAME)
        .flatMap(seq -> {
          var entity = toProductEntityModel(product);
          System.out.println("SEQUENCE ---- " + seq);
          entity.setProductId(Math.toIntExact(seq));
          return repository.save(entity).map(this::toProductRestModel);
        });
  }

  private Product toProductRestModel(ProductEntity entity) {
    return new Product(entity.getProductId(), entity.getName(), entity.getDescription());
  }

  private ProductEntity toProductEntityModel(Product rest) {
    return new ProductEntity(rest.id(), rest.name(), rest.description());
  }
  
}
