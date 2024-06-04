package dev.agasen.question.infrastructure.repository;

import org.springframework.stereotype.Component;

import dev.agasen.question.domain.QuestionAggregate;
import dev.agasen.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionRepositoryAdapter implements QuestionRepository {

  private final QuestionJpaRepository repository;

  @Override
  public void create(QuestionAggregate q) {
    repository.save(new Question(null, q.getTopic(), q.getQuestion()));
  }
  
}
