package com.quiz.Quiz.Application.DTOS;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
