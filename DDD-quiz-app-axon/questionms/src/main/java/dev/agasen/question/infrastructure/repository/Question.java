package dev.agasen.question.infrastructure.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Question {
  
  @Id
  private Long id;
  
  private String topic;
  private String question;
  
}
