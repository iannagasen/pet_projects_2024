package dev.agasen.question.application.command.questioncreated;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import dev.agasen.question.domain.QuestionAggregate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateQuestionCommandHandler {

  // private final Repository<QuestionAggregate> aggregateRepository;

  // @CommandHandler
  // public void handle(CreateQuestionCommand command) throws Exception {
  //   log.info("Handling Create Question Command");
  //   QuestionAggregate aggregate = new QuestionAggregate(
  //     command.getId(), 
  //     command.getTopic(), 
  //     command.getQuestion(), 
  //     command.getChoice());

  //   aggregateRepository.loadOrCreate("invalid", () -> aggregate);
  // }
  
  
}
