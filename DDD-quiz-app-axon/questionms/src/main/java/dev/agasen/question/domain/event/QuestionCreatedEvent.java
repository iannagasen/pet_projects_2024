package dev.agasen.question.domain.event;

import java.util.List;

import dev.agasen.question.domain.entity.Choice;

public record QuestionCreatedEvent(
  Long id,
  String topic,
  String question,
  List<Choice> choices
) {
  
}
