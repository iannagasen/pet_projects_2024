package dev.agasen.ecomm.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public record Review(
  Long id,
  LocalDateTime date,
  Rating rating,
  String user,
  List<String> imagePaths
) {

}
