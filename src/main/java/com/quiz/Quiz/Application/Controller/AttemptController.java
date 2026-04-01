package com.quiz.Quiz.Application.Controller;

import com.quiz.Quiz.Application.DTOS.AttemptDto;
import com.quiz.Quiz.Application.Entity.Attempt;
import com.quiz.Quiz.Application.Entity.Quiz;
import com.quiz.Quiz.Application.Entity.User;
import com.quiz.Quiz.Application.Repository.QuizRepository;
import com.quiz.Quiz.Application.Repository.UserRepository;
import com.quiz.Quiz.Application.Service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AttemptController {

    private final AttemptService attemptService;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @PostMapping("/submit")
    public ResponseEntity<Attempt> submitQuiz(@RequestBody AttemptDto attemptDto) {
        // 1. Current logged-in user ki email nikalo Security Context se
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Agar user nahi mila toh RuntimeException pheko
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Error: User with email " + email + " not found!"));

        // 2. Quiz nikalo ID se. Agar ID galat hai toh message do
        Quiz quiz = quizRepository.findById(attemptDto.getQuizId())
                .orElseThrow(() -> new RuntimeException("Error: Quiz with ID " + attemptDto.getQuizId() + " not found!"));

        // 3. Service ko call karke result calculate aur save karwao
        Attempt savedAttempt = attemptService.processAttempt(attemptDto, user, quiz);

        return ResponseEntity.ok(savedAttempt);
    }
}
