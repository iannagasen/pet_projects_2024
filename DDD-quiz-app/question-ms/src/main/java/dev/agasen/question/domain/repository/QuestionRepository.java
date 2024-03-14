package dev.agasen.question.domain.repository;

import dev.agasen.question.domain.QuestionAggregate;

public interface QuestionRepository {

  void create(QuestionAggregate question);
  
}
