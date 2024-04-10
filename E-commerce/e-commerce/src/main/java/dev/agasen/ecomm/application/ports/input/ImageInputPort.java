package dev.agasen.ecomm.application.ports.input;

import dev.agasen.ecomm.domain.entity.Image;

public interface ImageInputPort {
  
  Image getImage(Long id);

  Long saveImage(Image image);
  
}