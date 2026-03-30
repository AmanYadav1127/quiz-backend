package com.quiz.Quiz.Application.Repository;

import com.quiz.Quiz.Application.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    // Isse hum kisi specific quiz ke saare sawal ek saath nikal payenge
    List<Question> findByQuizId(Long quizId);
}
