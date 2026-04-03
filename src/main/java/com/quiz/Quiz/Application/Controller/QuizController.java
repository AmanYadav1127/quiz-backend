package com.quiz.Quiz.Application.Controller;

import com.quiz.Quiz.Application.DTOS.QuizDto;
import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin("*") // React frontend se connect karne ke liye zaroori hai
public class QuizController {
    private final QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<List<QuizDto>> fetchAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> fetchQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }
}
