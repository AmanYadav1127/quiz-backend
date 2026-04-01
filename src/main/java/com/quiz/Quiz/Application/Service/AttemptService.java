package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.DTOS.AttemptDto;
import com.quiz.Quiz.Application.Entity.Attempt;
import com.quiz.Quiz.Application.Entity.Question;
import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Entity.User;
import com.quiz.Quiz.Application.Repository.AttemptRepository;
import com.quiz.Quiz.Application.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttemptService {
    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;

    public Attempt processAttempt(AttemptDto attemptDto, User user, Quiz quiz) {
        int correctCount = 0;

        // Loop chalao har answer par jo DTO mein aaya hai
        for (AttemptDto.QuestionAnswer ans : attemptDto.getAnswers()) {
            Question question = questionRepository.findById(ans.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question nahi mila!"));

            // Logic: DB ka answer vs User ka selected option
            if (question.getAnswer().equalsIgnoreCase(ans.getSelectedOption())) {
                correctCount++;
            }
        }

        // Result save karne ke liye Attempt object banao
        Attempt attempt = new Attempt();
        attempt.setScore(correctCount);
        attempt.setTotalQuestions(attemptDto.getAnswers().size());
        attempt.setCreatedAt(LocalDateTime.now());
        attempt.setUser(user);
        attempt.setQuiz(quiz);

        return attemptRepository.save(attempt);
    }
}
