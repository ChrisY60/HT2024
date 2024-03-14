package com.hacktues.api.security;

import com.hacktues.api.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {
    @Value("${jwt.token.expiration}")
    private long expirationTime;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(User user) {
        String email = user.getEmail();
        String role = user.getRole().toString();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + (expirationTime * 1000));

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}