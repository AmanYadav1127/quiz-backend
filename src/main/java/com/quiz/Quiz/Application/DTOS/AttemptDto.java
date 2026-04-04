package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

import java.util.List;
@Data
public class AttemptDto {
    private Long quizId;
    private int score;          // <--- Ye dalo
    private int totalQuestions; // <--- Ye dalo
    private List<QuestionAnswer> answers;

    @Data
    public static class QuestionAnswer {
        private Long questionId;
        private String selectedOption;
    }
}