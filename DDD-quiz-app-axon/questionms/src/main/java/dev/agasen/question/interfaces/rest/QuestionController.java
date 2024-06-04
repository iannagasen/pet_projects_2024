package dev.agasen.question.interfaces.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.question.application.command.questioncreated.CreateQuestionCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
  
  private final CommandGateway commandGateway;

  @PostMapping
  public Long createQuestion(@RequestBody Question question) {
    log.info("Create Question API called.");

    CreateQuestionCommand command = new CreateQuestionCommand(
      new Random().nextLong(), 
      question.topic(), 
      question.question(),
       null);

    // Long type is from the AggregateIdentifierId type
    Long result = commandGateway.sendAndWait(command);
    return result;
  }
  

}
