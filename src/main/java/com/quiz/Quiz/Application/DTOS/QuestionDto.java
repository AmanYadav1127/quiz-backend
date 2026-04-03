package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

@Data
public class QuestionDto {
    private Long id;
    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
