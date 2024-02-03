package dev.agasen.imageupload;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageStorageService {
  
  private final ImageRepository imageRepository;
  private final ImageCompressionService imageCompressionService;

  public String uploadImage(MultipartFile file) {
    ImageData compressImage = toCompressImage(file);
    ImageData savedImage = imageRepository.save(compressImage);
    if (savedImage == null) return null;
    return "file uploaded successfully: " + file.getOriginalFilename();
  }

  public byte[] downloadImage(String fileName) {
    return  imageRepository.findByName(fileName)
        .map(ImageData::getImageData)
        .map(imageCompressionService::decompress)
        .orElseThrow();
  }

  private ImageData toCompressImage(MultipartFile file) {
    var image = new ImageData();
    image.setName(file.getOriginalFilename());
    image.setType(file.getContentType());
    try {
      byte[] compressImageData = imageCompressionService.compress(file.getBytes());
      image.setImageData(compressImageData);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }


}
