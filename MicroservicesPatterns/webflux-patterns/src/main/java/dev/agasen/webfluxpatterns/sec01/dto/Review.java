package dev.agasen.webfluxpatterns.sec01.dto;

public record Review(
  Integer id,
  String user,
  Integer rating,
  String comment
) {

}
