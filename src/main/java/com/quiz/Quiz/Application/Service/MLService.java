package com.quiz.Quiz.Application.Service;

import com.quiz.Quiz.Application.DTOS.MLRequestDto;
import com.quiz.Quiz.Application.DTOS.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MLService {
    private final RestTemplate restTemplate;
    private final String PYTHON_API_URL = "http://localhost:5000/generate"; // Dost se URL confirm kar lena

    public List<QuestionDto> generateQuestions(String paragraph) {
        // 1. Request Body taiyar karo
        MLRequestDto request = new MLRequestDto();
        request.setParagraph(paragraph);

        try {
            // 2. Python API ko POST request bhejo
            // Hum QuestionDto[] (Array) le rahe hain kyunki Python list bhej raha hai
            QuestionDto[] response = restTemplate.postForObject(PYTHON_API_URL, request, QuestionDto[].class);

            if (response != null) {
                return Arrays.asList(response);
            }
        } catch (Exception e) {
            System.out.println("ML API Connection Failed: " + e.getMessage());
        }

        return new ArrayList<>(); // Agar fail hua toh khali list bhej do
    }
}
