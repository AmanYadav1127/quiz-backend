package com.quiz.Quiz.Application.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Integer id;
    private String question;
    private List<String> options;
    @JsonProperty("correct_answer")
    private String correctAnswer;
}
