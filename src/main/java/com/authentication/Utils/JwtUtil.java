package com.authentication.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JwtUtil {

    @Autowired
    private static Environment env;
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(env.getProperty("jwt_secret_key").getBytes(StandardCharsets.UTF_8));
    private static final String ISSUER = "AuthenticationService";

    public static String GenerateToken(String username, int roleId){
        Map<String, Object> claims = new HashMap<>(){};
        claims.put("role", roleId);

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h due
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .addClaims(claims)
                .compact();
    }

    public static boolean VerifyToken(String token, int roleId){
        JwtParser jwtParser =
                Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .requireIssuer(ISSUER)
                        .require("role", roleId)
                .build();

        try{
            Claims claims = jwtParser.parseClaimsJws(token).getBody();

            //check if token expired
            if(claims.getExpiration().before(new Date()))
                return false;

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
