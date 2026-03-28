package com.quiz.Quiz.Application.Repository;

import com.quiz.Quiz.Application.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
        Optional<User>findByEmail(String email);
}
