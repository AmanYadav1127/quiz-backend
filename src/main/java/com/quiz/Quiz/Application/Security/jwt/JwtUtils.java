package com.quiz.Quiz.Application.Security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;


public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final String jwtSecret = "mySecretKeymySecretKeymySecretKey12345";
    private final int jwtExpirationMs = 86400000;
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateJwtToken(UserDetails userDetails)
    {
        String username = userDetails.getUsername();
        return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
                .signWith(getSigningKey()).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

        public boolean validateJwtToken(String authToken) {
            try {
                Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(authToken);
                return true;
            } catch (JwtException e) {
                logger.error("Invalid JWT token: {}", e.getMessage());
            }
            catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty: {}", e.getMessage());
            }
            return false;
        }
}
