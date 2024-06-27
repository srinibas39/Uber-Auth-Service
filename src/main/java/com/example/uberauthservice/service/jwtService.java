package com.example.uberauthservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class jwtService implements CommandLineRunner {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiry}")
    private int expiry;


    private String createToken(Map<String , Object> payload ,  String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSignInKey())
                .compact();
    }

    private Claims extractAllPayload(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return  key;
    }

    private <T> T extractClaim(String token , Function<Claims , T> claimsResolver){
        final Claims claims = extractAllPayload(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }

    private String getEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }

    private String getPhone(String token){
        return extractClaim(token , Claims -> Claims.get("phone", String.class));
    }


    @Override
    public void run(String... args) throws Exception {
        Map<String , Object> payload = new HashMap<>();
        payload.put("email","abc12@gmail.com");
        payload.put("password","123456");
        payload.put("phone","6789978568");
        String token = createToken(payload,"srinibas");
        Date expiredDate = getExpiration(token);
        String email = getEmail(token);
        String phone = getPhone(token);
        System.out.println("GeneratedCode "+token);
        System.out.println("Expiration date "+expiredDate);
        System.out.println("email "+email);
        System.out.println("phone "+phone);
    }
}
