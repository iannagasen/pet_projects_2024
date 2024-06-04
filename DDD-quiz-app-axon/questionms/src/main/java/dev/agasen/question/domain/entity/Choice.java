package dev.agasen.question.domain.entity;

public record Choice(
  Long id,
  String value,
  String version,
  boolean correct
) {
}
