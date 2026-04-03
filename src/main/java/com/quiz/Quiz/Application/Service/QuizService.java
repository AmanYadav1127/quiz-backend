package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.DTOS.QuizDto;
import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    // 1. Naya Quiz create karne ka logic
    public Quiz createQuiz(Quiz quiz) {
        // Har question ko batana padega ki wo isi quiz ka hissa hai
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
        }
        return quizRepository.save(quiz);
    }

    // 2. Saare Quizzes ki list fetch karna (Dashboard ke liye)
    public List<QuizDto> getAllQuizzes() {
        List<Quiz> quizzes= quizRepository.findAll();
        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .collect(Collectors.toList());
    }

    // 3. Ek specific Quiz ko uski ID se nikalna
    public QuizDto getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with quiz id : " + id));
        // Entity ko DTO mein badlo bina kisi extra mapper class ke
        return modelMapper.map(quiz, QuizDto.class);
    }
}
