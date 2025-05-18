package com.jwt.RoleBasedAuth.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jwt.RoleBasedAuth.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String secretKey = "yourverysecurestaticsecretkeythatislongenoughdetermine";

    private final long expiration = 1000 * 60 * 60;

    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
    // private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(UserDetails username) {
        return Jwts.builder()
                .setSubject(username.getUsername())
                .claim("role", extractRole(username))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .signWith(key)
                .compact();
    }

    private String extractRole(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElse("USER");
    }

    public String extractUsernamefromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SecretKey getSecretKey() {
        return key;
    }

}
