package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

import java.util.List;
@Data
public class AttemptDto {
    private Long quizId; // Kaunsa quiz attempt kiya
    private List<QuestionAnswer> answers; // User ne jo answers diye unki list

    @Data
    public static class QuestionAnswer {
        private Long questionId;
        private String selectedOption;
    }
}
