package com.svyter.spring.swimingbysvyter.security;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import io.jsonwebtoken.Claims;


import java.util.Date;
import java.util.function.Function;

public interface JwtUtils {
    String generateToken(Customers customers);
    String extractSubject(String token);
    long extractUserId(String token);
    Date extractIssuedAt(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims,T> funClaims);
    boolean isTokenExpired(String token);
}
