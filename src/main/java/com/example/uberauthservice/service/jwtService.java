package com.example.uberauthservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class jwtService implements CommandLineRunner {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiry}")
    private int expiry;


    private String createToken(Map<String , Object> payload ,  String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(username)
                .signWith(key)
                .compact();
    }


    @Override
    public void run(String... args) throws Exception {
        Map<String , Object> payload = new HashMap<>();
        payload.put("email","abc12@gmail.com");
        payload.put("password","123456");
        String res = createToken(payload,"srinibas");
        System.out.println("GeneratedCode"+res);
    }
}
