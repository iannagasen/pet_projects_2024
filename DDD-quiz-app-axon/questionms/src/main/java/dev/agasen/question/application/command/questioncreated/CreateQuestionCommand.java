package dev.agasen.question.application.command.questioncreated;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import dev.agasen.question.domain.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionCommand {
  @TargetAggregateIdentifier
  private Long id;
  private String topic;
  private String question;
  private List<Choice> choice;
}
