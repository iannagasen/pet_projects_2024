package dev.agasen.catalog.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileLoader {
  
  public Optional<String> loadFileAsString(final String path) {  
    try {
      File resource = new ClassPathResource(path).getFile();
      return Optional.of(new String(Files.readAllBytes(resource.toPath())));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return Optional.empty();
    }
  }

}
