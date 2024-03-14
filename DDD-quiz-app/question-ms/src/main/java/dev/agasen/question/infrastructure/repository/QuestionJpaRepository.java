package dev.agasen.question.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuestionJpaRepository extends JpaRepository<Question, Long> {
  
}
