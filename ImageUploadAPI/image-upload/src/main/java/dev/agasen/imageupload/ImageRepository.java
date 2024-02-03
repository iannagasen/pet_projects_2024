package dev.agasen.imageupload;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData, Long> {

  Optional<ImageData> findByName(String fileName);
  
}
