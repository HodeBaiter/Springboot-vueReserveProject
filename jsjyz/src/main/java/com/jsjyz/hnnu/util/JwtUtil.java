package com.jsjyz.hnnu.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {
    private String slat = "jsjyz";
    public String create(Long userId, String permissions){
            JwtBuilder builder = Jwts.builder();
            Map<String, Object> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("userId",userId);
            stringStringHashMap.put("permissions",permissions);
        long month = 1000 * 60 * 60 * 24 * 30L;
        String token = builder
                    //header
                    .setHeaderParam("typ","JWT")
                    .setHeaderParam("alg","HS256")
                    //payload
                    .setClaims(stringStringHashMap)
                    .setSubject("create token when login")
                    .setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.DAYS)))
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(Date.from(Instant.now()))
                    .signWith(SignatureAlgorithm.HS256, slat)
                    .compact();
            return token;
    }

    public Map<String,Object> parse(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(slat).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }


}
