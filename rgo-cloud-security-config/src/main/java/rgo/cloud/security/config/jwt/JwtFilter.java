package rgo.cloud.security.config.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.getToken((HttpServletRequest) servletRequest);

        if (token != null && jwtProvider.tokenIsValid(token)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(jwtProvider.getAuthentication(token));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
