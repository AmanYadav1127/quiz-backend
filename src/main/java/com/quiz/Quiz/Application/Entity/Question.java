package com.quiz.Quiz.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//Ye har ek sawal aur uske options store karega.
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;   // Sawal kya hai?

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;    // Sahi jawab (e.g., "Option 1")

    // Many Questions belong to one Quiz
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
