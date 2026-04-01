package com.quiz.Quiz.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;       // Kitne sawal sahi hue
    private Integer totalQuestions; // Total kitne sawal the
    private LocalDateTime createdAt; // Kab attempt kiya (Date & Time)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;          // Kis user ne attempt kiya

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;          // Kaunsa quiz attempt kiya
}
