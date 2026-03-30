package com.quiz.Quiz.Application.Controller;

import com.quiz.Quiz.Application.DTOS.LoginRequest;
import com.quiz.Quiz.Application.DTOS.SignupRequest;
import com.quiz.Quiz.Application.Entity.User;
import com.quiz.Quiz.Application.Repository.UserRepository;
import com.quiz.Quiz.Application.Security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest)
    {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        User user=new User();
        user.setName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest)
    {
        //Spring Security internally:
        //DB se user fetch karta hai
        //password match karta hai
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        //current request/session me user authenticated mark ho gaya
        //baaki secured APIs ko pata chalega ki user login hai
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //getPrincipal() → logged-in user ki details deta hai
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("email", userDetails.getUsername());

        return ResponseEntity.ok(response);
    }

}
