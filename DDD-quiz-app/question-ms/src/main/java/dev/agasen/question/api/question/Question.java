package dev.agasen.question.api.question;

public record Question(
  Long id,
  String topic,
  String question
  // TODO: add Set<Choice>
)
{ }