package dev.agasen.ecomm.application.ports.output;

import dev.agasen.ecomm.domain.entity.CompressedImage;

public interface ImageOutputPort {
  
  CompressedImage getImage(Long id);

  Long saveImage(CompressedImage image);

}
