package com.quiz.Quiz.Application.Controller;

import com.quiz.Quiz.Application.DTOS.MLRequestDto;
import com.quiz.Quiz.Application.DTOS.QuestionDto;
import com.quiz.Quiz.Application.Service.MLService;
import com.quiz.Quiz.Application.Service.QuizService; // 1. Service Import karo
import com.quiz.Quiz.Application.Entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ml")
@RequiredArgsConstructor
public class MLController {

    private final MLService mlService;
    private final QuizService quizService; // 2. Isse inject karo (RequiredArgsConstructor handles this)

    @PostMapping("/generate")
    public ResponseEntity<Quiz> generateFromParagraph(@RequestBody MLRequestDto request) {

        // Step A: Paragraph se questions generate karwana (Python API call)
        List<QuestionDto> questions = mlService.generateQuestions(request.getText());

        // Step B: Generate hue questions ko Database mein save karna
        // Hum title abhi default "AI Generated Quiz" de rahe hain
        Quiz savedQuiz = quizService.createQuizFromML("AI Generated Quiz", questions);

        // Step C: Saved Quiz object (with ID) return karna
        return ResponseEntity.ok(savedQuiz);
    }
}