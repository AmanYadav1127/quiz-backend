package com.quiz.Quiz.Application.Repository;

import com.quiz.Quiz.Application.Entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt,Long> {
    // Kisi ek user ke saare results nikalne ke liye
    List<Attempt> findByUserId(Long userId);
}
