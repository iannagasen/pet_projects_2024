package dev.agasen.question.application;

import org.springframework.stereotype.Component;

import dev.agasen.question.api.question.Question;
import dev.agasen.question.domain.QuestionAggregate;
import dev.agasen.question.domain.command.CreateQuestionCommand;
import dev.agasen.question.domain.repository.QuestionRepository;
import dev.agasen.question.interfaces.application.QuestionService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;

  @Override
  public void createQuestion(Question question) {
    QuestionAggregate aggregate = new QuestionAggregate(new CreateQuestionCommand(question.topic(), question.question()));
    questionRepository.create(aggregate);
  }

  
}
