package dev.agasen.ecomm.domain.entity;

public record CompressedImage (
  long id,
  String name,
  String type,
  byte[] imageData
) { }