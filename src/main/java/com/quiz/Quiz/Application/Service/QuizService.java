package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.DTOS.QuestionDto;
import com.quiz.Quiz.Application.DTOS.QuizDto;
import com.quiz.Quiz.Application.Entity.Question;
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

    // 1. Naya Quiz create karne ka logic (ML integration ke liye updated)
    public Quiz createQuizFromML(String title, List<QuestionDto> questionDtos) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription("Quiz generated from given paragraph");
        // DTOs ko Entities mein badalna aur relationship set karna
        List<Question> questions = questionDtos.stream().map(dto -> {
            Question q = new Question();
            q.setQuestion(dto.getQuestion());
            q.setOptions(dto.getOptions());
            q.setCorrectAnswer(dto.getCorrectAnswer()); // DTO mein correct_answer ko correctAnswer kar dena
            q.setQuiz(quiz); // Back-reference set karna zaroori hai
            return q;
        }).collect(Collectors.toList());

        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }

    // 2. Normal Quiz create logic (Agar pura Quiz object aa raha ho)
    public Quiz createQuiz(Quiz quiz) {
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
        }
        return quizRepository.save(quiz);
    }

    // 3. Saare Quizzes ki list fetch karna (Dashboard ke liye)
    public List<QuizDto> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .collect(Collectors.toList());
    }

    // 4. Ek specific Quiz ko uski ID se nikalna
    public QuizDto getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with quiz id : " + id));

        return modelMapper.map(quiz, QuizDto.class);
    }
}