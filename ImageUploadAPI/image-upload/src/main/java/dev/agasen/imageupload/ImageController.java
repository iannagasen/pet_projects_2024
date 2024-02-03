package dev.agasen.imageupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {  
  
  private final ImageStorageService imageStorageService;

  @PostMapping
  public String uploadImage(@RequestBody MultipartFile image) {
    return imageStorageService.uploadImage(image);
  }

  @GetMapping(value = "/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] downloadImage(@PathVariable String fileName) {
    return imageStorageService.downloadImage(fileName);
  }

  @GetMapping("/test")
  public String getMethodName() {
      return "testing";
  }
  
  
}
