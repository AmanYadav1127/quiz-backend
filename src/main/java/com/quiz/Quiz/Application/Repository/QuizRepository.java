package com.quiz.Quiz.Application.Repository;

import com.quiz.Quiz.Application.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
