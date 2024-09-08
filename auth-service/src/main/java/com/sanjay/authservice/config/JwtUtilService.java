package com.sanjay.authservice.config;

import com.sanjay.authservice.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtUtilService {
    private final String jwtSigningKey;
    private final JwtParser jwtParser;

    public JwtUtilService(@Value("${jwt.signing-Key}") String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
        this.jwtParser = Jwts.parserBuilder().setSigningKey(jwtSigningKey.getBytes()).build();
    }
    public String createToken(String userName) {
        long currentTime = System.currentTimeMillis();
        Date issuedAt = new Date(currentTime);

        final long JWT_TOKEN_VALIDITY = TimeUnit.HOURS.toMillis(1);
        Date expirationDate = new Date(currentTime + JWT_TOKEN_VALIDITY);
        SecretKey signingKey = Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(signingKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

  /*  public String getUserRole(String token) {
        var role = "";
        final Claims claims = getClaimFromTokens(token);
        if (claims != null) role = String.valueOf(Optional.ofNullable(claims.get(Constants.KWTDetails.USER_ROLE)).orElse(""));
        return role;
    }*/

   /* private Claims getClaimFromTokens(String token) {
        Claims claims;
        claims = Jwts.parser().setSigningKey(encode(jwtSigningKey.getBytes())).parseClaimsJws(token).getBody();
        return claims;
    }

    private static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }*/
}
