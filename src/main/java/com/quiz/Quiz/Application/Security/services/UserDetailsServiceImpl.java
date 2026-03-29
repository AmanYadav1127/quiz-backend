package com.quiz.Quiz.Application.Security.services;

import com.quiz.Quiz.Application.Entity.User;
import com.quiz.Quiz.Application.Repository.UserRepository;
import com.quiz.Quiz.Application.Security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

//  Ye class Spring Security ko batati hai user DB se kaise fetch karna hai
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //  DB access ke liye repository
    private final UserRepository userRepository;

    // Spring Security login ke time ye method call karta hai
    @Override
    public UserDetails loadUserByUsername(String email) {

        //  email ke basis pe user fetch kar rahe hain
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  DB user ko convert kar rahe hain security format me
        return UserDetailsImpl.build(user);
    }
}