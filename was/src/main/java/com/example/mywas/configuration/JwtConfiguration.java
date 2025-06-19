package com.example.mywas.configuration;

import com.example.mywas.domain.order.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtConfiguration {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // secret key
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 유효시간 1시간

    // 토큰 생성
    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰에서 유저명 추출
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 토큰에서 정보 추출
    public Map<String, Object> getTokenInfo(String token){
        if(!validateToken(token)){ // 토큰이 유효한지 확인
            return null;
        }else{
            Map<String, Object> dataMap = new HashMap<>();
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();

            dataMap.put("username",claims.getSubject());
            dataMap.put("userid", claims.get("id"));
            dataMap.put("role", claims.get("role"));
            return dataMap;
        }
    }

}
