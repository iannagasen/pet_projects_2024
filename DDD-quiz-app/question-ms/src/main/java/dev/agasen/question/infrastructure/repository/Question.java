package dev.agasen.question.infrastructure.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String question;

  private String topic;
  
}