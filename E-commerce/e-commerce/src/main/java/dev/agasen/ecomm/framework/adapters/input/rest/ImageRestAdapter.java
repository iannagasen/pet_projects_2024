package dev.agasen.ecomm.framework.adapters.input.rest;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.agasen.ecomm.application.usecases.ImageUseCase;
import dev.agasen.ecomm.domain.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageRestAdapter {

  private final ImageUseCase imageUseCase;

  @GetMapping(value = "/{id}", produces = {
    MediaType.IMAGE_JPEG_VALUE,
    MediaType.IMAGE_PNG_VALUE
  })
  public byte[] getImage(@PathVariable Long id) {
    byte[] data = imageUseCase.getImage(id).imageData();
    return data;
  }


  @PostMapping
  public Long uploadImage(@RequestBody MultipartFile file) {
    Image image;
    try {
      image = new Image(0, file.getOriginalFilename(), file.getContentType(), file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return imageUseCase.saveImage(image);
  }
  
}
