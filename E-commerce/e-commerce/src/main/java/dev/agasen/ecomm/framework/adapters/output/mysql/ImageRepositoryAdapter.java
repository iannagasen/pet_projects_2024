package dev.agasen.ecomm.framework.adapters.output.mysql;

import org.springframework.stereotype.Repository;

import dev.agasen.ecomm.application.ports.output.ImageOutputPort;
import dev.agasen.ecomm.domain.entity.CompressedImage;
import dev.agasen.ecomm.framework.adapters.output.mysql.mappers.ImageMapper;
import dev.agasen.ecomm.framework.adapters.output.mysql.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryAdapter implements ImageOutputPort {

  private final ImageRepository imageRepository;
  private final ImageMapper imageMapper;

  @Override
  public CompressedImage getImage(Long id) {
    return imageRepository.findById(id)
        .map(imageMapper::toDomainModel)
        .orElseThrow();
  }

  @Override
  public Long saveImage(CompressedImage image) {
    return imageRepository
        .save(imageMapper.toDataModel(image))
        .getId();
  }
  
}
