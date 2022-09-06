package com.jsjyz.hnnu.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {
    private long month = 1000*60*60*24*30;
    private String slat = "jsjyz";
    public String create(Long userId, String permissions){
            JwtBuilder builder = Jwts.builder();
            Map<String, Object> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("userId",userId);
            stringStringHashMap.put("permissions",permissions);
            String token = builder
                    //header
                    .setHeaderParam("typ","JWT")
                    .setHeaderParam("alg","HS256")
                    //payload
                    .setClaims(stringStringHashMap)
                    .setSubject("create token when login")
                    .setExpiration(new Date(System.currentTimeMillis() + month))
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, slat)
                    .compact();
            return token;
    }

    public Map<String,Object> parse(String token){
        try {
      System.out.println(new Date(System.currentTimeMillis() + month));
            Jwt parse = Jwts.parser().setSigningKey(slat).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

  public static void main(String[] args) {

  }
}
