package lk.elib.elibrarybackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.elib.elibrarybackend.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationInMs}")
    private long jwtExpirationDate;

    @Value("${jwt.refreshExpirationInMs}")
    private long refreshTokenExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("type", "access_token")
                .signWith(key())
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + refreshTokenExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expireDate)
                .claim("type", "refresh_token")
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = getClaims(token);
        return claims.getBody().get("type").toString().equals("access_token");
    }

    public Jws<Claims> getClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);

        } catch (MalformedJwtException e) {
            throw new AuthenticationException("Invalid JWT token: {" + e.getMessage() + "}");

        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT token is expired: {" + e.getMessage() + "}");

        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT token is unsupported: {" + e.getMessage() + "}");

        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT claims string is empty: {" + e.getMessage() + "}");
        }
    }

    public String refreshToken(String token) {
        Jws<Claims> claims = getClaims(token);

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        if (claims.getBody().get("type").toString().equals("refresh_token")) {
            return Jwts.builder()
                    .setSubject(claims.getBody().getSubject())
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .signWith(key())
                    .compact();
        }

        return null;
    }
}
