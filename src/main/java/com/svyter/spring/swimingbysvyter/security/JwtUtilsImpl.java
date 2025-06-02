package com.svyter.spring.swimingbysvyter.security;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtilsImpl implements JwtUtils{
    private final String SECRET = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
    private final int EXPIRATION_TIME = 60 * 60 * 100000; // 360000000 мс. = 1ч

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Customers customers) {
        return Jwts.builder()
                .claim("customerId", customers.getId())
                .subject(customers.getEmail()) // для кого токен
                .issuedAt(new Date())  // дата выпуска токена
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // установка срока годности
                .signWith(getSigningKey()) // подпись секретным ключем
                .compact(); // превращаем токен в строку
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public long extractUserId(String token) {
        return (long) (int) extractClaim(token, n -> n.get("customerId"));
    }

    public Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> funClaims) {
        Claims claims = extractAllClaims(token);
        return funClaims.apply(claims);
    }

    private Claims extractAllClaims(String token){
        JwtParser parser = Jwts.parser().verifyWith(getSigningKey()).build();
        return parser.parseSignedClaims(token.replace("Bearer ", "")).getPayload();
    }

    public boolean isTokenValid(String token, String customerName) {
        final String extractedCustomerName = extractSubject(token);
        return extractedCustomerName.equals(customerName) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
