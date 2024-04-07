package dev.agasen.ecomm.application.usecases;

import org.springframework.stereotype.Service;

import dev.agasen.ecomm.application.ports.input.ImageInputPort;
import dev.agasen.ecomm.application.ports.output.ImageOutputPort;
import dev.agasen.ecomm.domain.entity.CompressedImage;
import dev.agasen.ecomm.domain.entity.Image;
import dev.agasen.ecomm.domain.services.ImageDataCompressor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageUseCase implements ImageInputPort {

  private final ImageOutputPort imageOutputPort;

  @Override
  public Image getImage(Long id) {
    return ImageDataCompressor
        .withCompressedImage(imageOutputPort.getImage(id))
        .decompress();
  }

  @Override
  public Long saveImage(Image image) {
    CompressedImage compressedImage = ImageDataCompressor
        .withImage(image).compress();
    return imageOutputPort.saveImage(compressedImage);
  }
  
}
