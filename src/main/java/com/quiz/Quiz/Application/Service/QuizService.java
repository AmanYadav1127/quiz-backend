package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    // 1. Naya Quiz create karne ka logic
    public Quiz createQuiz(Quiz quiz) {
        // Har question ko batana padega ki wo isi quiz ka hissa hai
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
        }
        return quizRepository.save(quiz);
    }

    // 2. Saare Quizzes ki list fetch karna (Dashboard ke liye)
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // 3. Ek specific Quiz ko uski ID se nikalna
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with quiz id : " + id));
    }
}
