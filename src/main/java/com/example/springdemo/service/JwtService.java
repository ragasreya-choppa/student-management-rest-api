package com.example.springdemo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;

@Service
public class JwtService {

    private final SecretKey signingKey;

    private final long expirationMS;

    private final String issuer;

    private final String audience;

    private final JwtParser jwtParser;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("{app.jwt.expiration-ms}") long expirationMS,
                      @Value("{app.jwt.issuer}") String issuer,
                      @Value("{app.jwt.audience}") String audience)
    {
        this.signingKey= Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        if(expirationMS<=0)
        {
            throw new IllegalArgumentException("JWT expiration must be positive");
        }

        this.expirationMS = expirationMS;
        this.issuer = issuer;
        this.audience = audience;

        this.jwtParser = Jwts.parser()
                .verifyWith(signingKey)
                .requireIssuer(issuer)
                .requireAudience(audience)

                .sig()
                .clear()
                .add(Jwts.SIG.HS256)
                .and()

                .build();
    }

    public String generateToken(UserDetails userDetails)
    {
        Instant now= Instant.now();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuer(issuer)
                .audience().add(audience).and()
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationMS)))
                .signWith(signingKey,Jwts.SIG.HS256)
                .compact();

    }

    public Claims validateToken(String token)
    {
        Claims claims = jwtParser.parseSignedClaims(token)
                .getPayload();

        if(claims.getSubject() == null || claims.getSubject().isBlank() || claims.getExpiration() == null)
        {
            throw new JwtException("Required JWt claims are missing!");
        }

        return claims;

    }

    public long getExpirationMS(){
        return expirationMS;
    }


}
