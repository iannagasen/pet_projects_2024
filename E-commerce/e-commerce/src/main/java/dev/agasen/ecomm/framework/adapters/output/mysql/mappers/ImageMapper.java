package dev.agasen.ecomm.framework.adapters.output.mysql.mappers;

import org.springframework.stereotype.Component;

import dev.agasen.ecomm.domain.entity.CompressedImage;
import dev.agasen.ecomm.framework.adapters.output.mysql.data.ImageData;

@Component
public class ImageMapper implements BaseMapper<ImageData, CompressedImage>{

  @Override
  public ImageData toDataModel(CompressedImage domain) {
    return new ImageData(
      domain.name(), 
      domain.type(), 
      domain.imageData());
  }

  @Override
  public CompressedImage toDomainModel(ImageData t) {
    return new CompressedImage(t.getId(), t.getName(), t.getType(), t.getImageData());
  }

}
