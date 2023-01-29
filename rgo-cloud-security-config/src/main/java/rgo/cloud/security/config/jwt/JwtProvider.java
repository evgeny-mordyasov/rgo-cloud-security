package rgo.cloud.security.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import rgo.cloud.security.config.jwt.properties.JwtProperties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtProvider {
    private final UserDetailsService service;
    private final JwtProperties config;

    public JwtProvider(UserDetailsService service, JwtProperties config) {
        this.service = service;
        this.config = config;
    }

    public String createToken(String mail) {
        Date currentDate = new Date();

        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(mail))
                .setIssuedAt(currentDate)
                .setExpiration(getExpirationDate(currentDate))
                .signWith(SignatureAlgorithm.HS256, config.getSecret())
                .compact();
    }

    private Date getExpirationDate(Date date) {
        return new Date(date.getTime() + config.getExpirationHours() * 3600000L);
    }

    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(config.getAuthCookieName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = service.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(config.getSecret())
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
