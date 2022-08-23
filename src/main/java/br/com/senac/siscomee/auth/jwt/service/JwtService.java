package br.com.senac.siscomee.auth.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senac.siscomee.auth.jwt.SecretKeyProvider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {

    private static final String ISSUER = "br.com.senac";

    @Autowired
    private SecretKeyProvider secretKeyProvider;

    public String generateToken(String username) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now(UTC).plusMinutes(120).toInstant(UTC));
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey);
        
        String token = builder.compact();    
        return token;
    }

    public String verifyToken(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getSubject();
    }
    
    public String verificarStatusToken(String token) {
        byte[] secretKey;
        Jws<Claims> claims = null;
		try {
			secretKey = secretKeyProvider.getKey();
			claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		} catch (IOException e) {
			return "IO exception";
		}catch(ExpiredJwtException ex) {
			return "Token expirado";
		}
		return claims.getBody().getSubject();
    }
}
