package edu.icet.ecom.service;

import edu.icet.ecom.entity.UserEntity;
import edu.icet.ecom.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private final String SECRET_KEY= "1167797a2688b4de6ad23076c60c62ad184e4669b0df8e10fde3d463a0e0d0db";

     public String extractUserName(String token){
         return extractClaims(token,Claims::getSubject);
     }

     public boolean isValid(String token , UserDetails user){
         String username = extractUserName(token);
         return (username.equals(user.getUsername())) && !isTokenExpired(token);
     }

    private boolean isTokenExpired(String token) {
         return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
         return extractClaims(token,Claims::getExpiration);
    }


    public <T> T extractClaims(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(UserEntity user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSingInKey())
                .compact();

        return token;
    }

    private SecretKey getSingInKey(){
        byte []  keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
