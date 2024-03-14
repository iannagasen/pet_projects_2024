package dev.agasen.question.domain.command;

public record CreateQuestionCommand(
  String topic,
  String question
) {
}
