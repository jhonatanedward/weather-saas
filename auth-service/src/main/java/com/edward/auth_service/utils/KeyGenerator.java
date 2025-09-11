package com.edward.auth_service.utils;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class KeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        System.out.println(java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}