package dev.agasen.question.domain;

import dev.agasen.question.domain.command.CreateQuestionCommand;
import lombok.Getter;

@Getter
public class QuestionAggregate{
  private Long id;
  private String topic;
  private String question;

  public QuestionAggregate(CreateQuestionCommand cmd) {
    this.topic = cmd.topic();
    this.question = cmd.question();
  }

}
