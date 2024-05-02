package com.yourhome.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yourhome.entity.BookingUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.issuer}")
    private String issuer;
    @Value("${token.time.duration}")
    private int timeDuration;

    private Algorithm algorithm;
    private final String USER_NAME="username";

    @PostConstruct
    public void constructTokenAlgorithm(){
        algorithm=Algorithm.HMAC256(secretKey);

    }

    public String tokenGenerator(BookingUser bookingUser){
        return JWT.create()
                .withClaim(USER_NAME,bookingUser.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+timeDuration))
                .sign(algorithm);
    }


    public String getUsername(String substring) {
        DecodedJWT decodedToken = JWT.require(algorithm).withIssuer(issuer).build().verify(substring);
           return decodedToken.getClaim(USER_NAME).toString();


    }
}
