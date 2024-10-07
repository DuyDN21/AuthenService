package com.authentication.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Date;

public class JwtUtil {

    @Autowired
    private static Environment env;

    public static String GenerateToken(String username){
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Hết hạn sau 1 giờ
//                .signWith(SignatureAlgorithm.ES256, env.getProperty("jwt_secret_key"))
//                .compact();
    }


}
