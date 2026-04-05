package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.DTOS.AttemptDto;
import com.quiz.Quiz.Application.Entity.Attempt;
import com.quiz.Quiz.Application.Entity.Question;
import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Entity.User;
import com.quiz.Quiz.Application.Repository.AttemptRepository;
import com.quiz.Quiz.Application.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttemptService {
    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public AttemptDto processAttempt(AttemptDto attemptDto, User user, Quiz quiz) {
        int correctCount = 0;

        // Loop chalao har answer par jo DTO mein aaya hai
        for (AttemptDto.QuestionAnswer ans : attemptDto.getAnswers()) {
            Question question = questionRepository.findById(ans.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question nahi mila!"));

            // Logic: DB ka answer vs User ka selected option
            if (question.getCorrectAnswer().equalsIgnoreCase(ans.getSelectedOption())) {
                correctCount++;
            }
        }

        Attempt attempt = new Attempt();
        attempt.setScore(correctCount);
        attempt.setTotalQuestions(attemptDto.getAnswers().size());
        attempt.setCreatedAt(LocalDateTime.now());
        attempt.setUser(user);
        attempt.setQuiz(quiz);

        Attempt savedAttempt = attemptRepository.save(attempt);

        // FIX: ModelMapper ki jagah manually data set karo taaki null na aaye
        AttemptDto responseDto = new AttemptDto();
        responseDto.setQuizId(quiz.getId());
        responseDto.setAnswers(attemptDto.getAnswers()); // Wahi answers wapas bhej do jo user ne bheje the
        // Agar tune DTO mein score field rakhi hai toh:
        // responseDto.setScore(correctCount);

        return responseDto;
    }

    // AttemptService ke andar niche ye add kar:
    public List<Attempt> getUserHistory(Long userId) {
        return attemptRepository.findByUserId(userId);
    }
}
