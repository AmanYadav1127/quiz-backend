package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

import java.util.List;
@Data
public class QuizDto {
    private Long id;
    private String title;
    private String description;
    private List<QuestionDto> questions; // Automatically map ho jayega
}
