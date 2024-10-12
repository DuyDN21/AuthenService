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
    private static final SecretKey SECRET_REFRESH_KEY =
            Keys.hmacShaKeyFor(env.getProperty("jwt_secret_refresh_key").getBytes(StandardCharsets.UTF_8));
    private static final String ISSUER = "AuthenticationService";
    private static final int REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000; // 1 week
    private static final int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 15; // 15 min

    public static String GenerateAccessToken(String username){
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String GenerateRefreshToken(String username){
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SECRET_REFRESH_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean VerifyAccessToken(String token, int roleId){
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

    public static boolean VerifyRefreshToken(String token){
        JwtParser jwtParser =
                Jwts.parserBuilder()
                        .setSigningKey(SECRET_REFRESH_KEY)
                        .requireIssuer(ISSUER)
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
