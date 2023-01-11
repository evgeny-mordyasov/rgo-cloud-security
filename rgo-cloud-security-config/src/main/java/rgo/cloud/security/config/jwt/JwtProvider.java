package rgo.cloud.security.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import rgo.cloud.security.config.jwt.properties.JwtProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtProvider {
    public final static String TOKEN_PREFIX = "Bearer_";

    private final UserDetailsService service;
    private final JwtProperties jwtProperties;

    public JwtProvider(UserDetailsService service, JwtProperties jwtProperties) {
        this.service = service;
        this.jwtProperties = jwtProperties;
    }

    public String createToken(String mail) {
        Date currentDate = new Date();

        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(mail))
                .setIssuedAt(currentDate)
                .setExpiration(getExpirationDate(currentDate))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    private Date getExpirationDate(Date date) {
        return new Date(date.getTime() + jwtProperties.getExpirationHours() * 3600000);
    }

    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = service.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean tokenIsValid(String token) {
        try {
            return service.loadUserByUsername(getUsername(token))
                    .isEnabled();
        } catch (Exception ignored) {
            return false;
        }
    }
}
