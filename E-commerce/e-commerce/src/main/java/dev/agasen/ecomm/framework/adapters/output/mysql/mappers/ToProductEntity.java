package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

import java.util.function.Function;
import dev.agasen.ecomm.domain.entity.Product;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ImagePathData;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ProductData;

public class ToProductEntity implements Function<ProductData, Product> {

  @Override
  public Product apply(ProductData t) {
    var p = new Product(
      t.getId(), 
      t.getTitle(), 
      t.getDescription(), 
      t.getImagePaths().stream().map(ImagePathData::getPath).toList()
     );
    return p;
  }
  
}
