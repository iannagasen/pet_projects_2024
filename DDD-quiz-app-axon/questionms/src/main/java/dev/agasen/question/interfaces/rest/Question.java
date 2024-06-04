package dev.agasen.question.interfaces.rest;

public record Question(
  Long id,
  String topic,
  String question
  // List<Choice> choices
) {
}