package org.example.onlinecourse.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
    @Value("${jwt.secret-key}")
    String SECRET_KEY;
    @Value("${jwt.access_expired}")
    Long accessTokenExpired;
    @Value("${jwt.refresh_expired}")
    Long refreshTokenExpired;
    public String generateAccessToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+accessTokenExpired))
                .signWith(getSignInKey())
                .addClaims(getClaims(userDetails))
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+refreshTokenExpired))
                .signWith(getSignInKey())
                .addClaims(getClaims(userDetails))
                .compact();
    }
    public Claims parse(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token).getBody();
    }
    private Map<String, Object> getClaims(UserDetails userDetails){
        return   Map.of(
                "roles",
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }
    public String getUsernameInToken(String token){
        return parse(token).getSubject();
    }
    public Boolean tokenIsExpired(String token){
        return parse(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }
    private Key getSignInKey(){
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }
}
