package dev.agasen.question.domain;

import java.util.List;
import java.util.Random;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import dev.agasen.question.application.command.questioncreated.CreateQuestionCommand;
import dev.agasen.question.domain.entity.Choice;
import dev.agasen.question.domain.event.QuestionCreatedEvent;

@Aggregate
public class QuestionAggregate {
  
  @AggregateIdentifier
  private Long id;

  private String topic;
  private String question;
  private List<Choice> choices;
  
  public QuestionAggregate() {
    // new question created
  }

  @CommandHandler
  public QuestionAggregate(CreateQuestionCommand command) {

    this.id = new Random().nextLong();
    
    QuestionCreatedEvent event = new QuestionCreatedEvent(
      this.id, 
      command.getTopic(), 
      command.getQuestion(), 
      command.getChoice());
    AggregateLifecycle.apply(event);
  }

}
