package com.auction.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class TokenValidator {
    @Value("${jwt.secret}")
    private static String secretKeyString;
    private static final String SECRET_KEY_STRING = "Tuz5jLZuH1mfX790wIu1Fei2Mud0t3JpEU8+MKUPjrw";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    public static boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getEncoded())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            if (expiration == null || expiration.before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
