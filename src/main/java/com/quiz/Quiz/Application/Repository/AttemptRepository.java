package com.quiz.Quiz.Application.Repository;

import com.quiz.Quiz.Application.DTOS.AttemptDto;
import com.quiz.Quiz.Application.Entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt,Long> {
    // Kisi ek user ke saare results nikalne ke liye
//    @Query("SELECT a FROM Attempt a LEFT JOIN FETCH a.answers WHERE a.user.id = :userId")
    List<Attempt> findByUserId(Long userId);
}
