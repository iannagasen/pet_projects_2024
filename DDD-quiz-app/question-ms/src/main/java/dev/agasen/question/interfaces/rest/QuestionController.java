package dev.agasen.question.interfaces.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.agasen.question.api.question.Question;
import dev.agasen.question.interfaces.application.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

  private final QuestionService questionService;

  @PostMapping
  public void createQuestion(@RequestBody Question question) {
    log.info("Create question");
    questionService.createQuestion(question);
  }


}
