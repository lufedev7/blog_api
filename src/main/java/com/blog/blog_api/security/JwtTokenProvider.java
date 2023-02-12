package com.blog.blog_api.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.blog.blog_api.exceptions.BlogAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationsInMs;
    public String CreateToken(Authentication authentication){
        String userName = authentication.getName();
        Date dateActual = new Date();
        Date dateExpiration = new Date(dateActual.getTime() + jwtExpirationsInMs);
        String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).
        setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
        return token;
    }

    public String getUserNameOfJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody();
        return claims.getSubject();
    }

    public boolean validatedToken(String token){
        try{
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;
        }catch(SignatureException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT not Valid");
        }catch(MalformedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT not Valid");
        } catch(ExpiredJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT time out");
        }catch(UnsupportedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT not compatible");
        }catch(IllegalArgumentException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT chain claims it's empty");
        }
    }
}
