package com.edward.weatherbff.adapters.inbound.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
public class JwtUtil {

    @Autowired
    private final PublicKey publicKey;

    public JwtUtil(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Long extractUserId(String token) {
        return parseToken(token).get("id", Long.class);
    }

    public String extractRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }
}