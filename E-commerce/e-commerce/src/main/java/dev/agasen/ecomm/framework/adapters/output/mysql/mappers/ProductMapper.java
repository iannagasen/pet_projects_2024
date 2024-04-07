package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

import org.springframework.stereotype.Component;

import dev.agasen.ecomm.domain.entity.Product;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ImagePathData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ProductData;

@Component
public class ProductMapper implements BaseMapper<ProductData, Product>{

  @Override
  public ProductData toDataModel(Product t) {
    throw new UnsupportedOperationException("Unimplemented method 'toDomainModel'");
  }

  @Override
  public Product toDomainModel(ProductData t) {
    var p = new Product(
      t.getId(), 
      t.getTitle(), 
      t.getDescription(), 
      t.getImagePaths().stream().map(ImagePathData::getPath).toList()
     );
    return p;
  }
  
}
