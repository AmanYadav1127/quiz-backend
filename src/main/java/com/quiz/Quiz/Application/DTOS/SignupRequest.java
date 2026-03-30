package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}
