package com.example.demo.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String getEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getEmailFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}









//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;

//@Component
//public class JwtUtil {
//
//  private static final String SECRET_KEY = "news_portal";
//
//  private static final int TOKEN_VALIDITY = 3600 * 5;
//
//  public String getEmailFromToken(String token) {
//      return getClaimFromToken(token, Claims::getSubject);
//  }
//
//  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//      final Claims claims = getAllClaimsFromToken(token);
//      return claimsResolver.apply(claims);
//  }
//
//  private Claims getAllClaimsFromToken(String token) {
//      return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
//  }
//
//  public Boolean validateToken(String token, UserDetails userDetails) {
//      final String username = getEmailFromToken(token);
//      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//  }
//
//  private Boolean isTokenExpired(String token) {
//      final Date expiration = getExpirationDateFromToken(token);
//      return expiration.before(new Date());
//  }
//
//  public Date getExpirationDateFromToken(String token) {
//      return getClaimFromToken(token, Claims::getExpiration);
//  }
//
//  public String generateToken(UserDetails userDetails) {
//
//      Map<String, Object> claims = new HashMap<>();
//
//      return Jwts.builder()
//              .setClaims(claims)
//              .setSubject(userDetails.getUsername())
//              .setIssuedAt(new Date(System.currentTimeMillis()))
//              .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
//              .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//              .compact();
//  }
//}
//

