package com.quiz.Quiz.Application.Controller;

import com.quiz.Quiz.Application.DTOS.MLRequestDto;
import com.quiz.Quiz.Application.DTOS.QuestionDto;
import com.quiz.Quiz.Application.Service.MLService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ml")
@RequiredArgsConstructor
public class MLController {
    private final MLService mlService;

    @PostMapping("/generate")
    public ResponseEntity<List<QuestionDto>> generateFromParagraph(@RequestBody MLRequestDto request) {
        // Paragraph se questions generate karwana
        List<QuestionDto> questions = mlService.generateQuestions(request.getParagraph());
        return ResponseEntity.ok(questions);
    }
}
