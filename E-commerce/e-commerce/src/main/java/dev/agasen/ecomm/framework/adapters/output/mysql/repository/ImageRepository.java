package dev.agasen.ecomm.framework.adapters.output.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ImageData;

public interface ImageRepository extends JpaRepository<ImageData, Long> {
  
}
