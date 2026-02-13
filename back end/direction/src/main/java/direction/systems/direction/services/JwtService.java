package direction.systems.direction.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

  private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String gerarToken(String email) {

    return Jwts.builder()
      .setSubject(email)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
      .signWith(SECRET_KEY)
      .compact();
  }

  public String extrairEmail(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(SECRET_KEY)
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

}
