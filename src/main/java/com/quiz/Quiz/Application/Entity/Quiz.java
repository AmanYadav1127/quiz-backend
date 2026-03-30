package com.quiz.Quiz.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//Ye table define karega ki quiz ka topic kya hai
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // Quiz ka naam (e.g., Core Java)
    private String description; // Chota sa intro (e.g., 10 questions on OOPs)

    // Ek Quiz mein bahut saare Questions honge
    // cascade = ALL matlab agar Quiz delete hua toh uske sawal bhi delete ho jayenge
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;
}
