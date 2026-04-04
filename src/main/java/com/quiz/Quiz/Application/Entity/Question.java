package com.quiz.Quiz.Application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//Ye har ek sawal aur uske options store karega.
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    @ElementCollection
    private List<String> options;
    @Column(name = "correct_answer")
    private String correctAnswer;    // Sahi jawab (e.g., "Option 1")

    // Many Questions belong to one Quiz
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;
}
