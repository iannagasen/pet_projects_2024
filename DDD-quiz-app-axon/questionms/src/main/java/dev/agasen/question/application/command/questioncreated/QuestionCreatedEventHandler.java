package dev.agasen.question.application.command.questioncreated;

import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;

import dev.agasen.question.domain.event.QuestionCreatedEvent;
import dev.agasen.question.infrastructure.repository.Question;
import dev.agasen.question.infrastructure.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionCreatedEventHandler {
  
  private final QuestionRepository questionRepository;

  @EventSourcingHandler
  public void on(QuestionCreatedEvent event) {
    log.info("Accepting Question Created Event");
    Question entity = new Question(null, event.topic(), event.question());
    questionRepository.save(entity); 
  }

}
