package rgo.cloud.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import rgo.cloud.common.api.model.Role;
import rgo.cloud.security.config.jwt.JwtConfigurer;
import rgo.cloud.security.config.jwt.JwtProvider;
import rgo.cloud.security.config.rule.RolePrivilege;
import rgo.cloud.security.config.util.Endpoint;

@Configuration
@EnableWebSecurity
@Import(BeanConfig.class)
public class SecurityConfig {
    private final JwtConfigurer jwtConfigurer;

    public SecurityConfig(JwtProvider jwtProvider) {
        this.jwtConfigurer = new JwtConfigurer(jwtProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        configureBase(http);
        configureRequests(http);

        http
                .apply(jwtConfigurer)
                .and()
                .logout(logout -> logout
                        .logoutUrl(Endpoint.Authorization.BASE_URL + Endpoint.Authorization.LOGOUT)
                        .logoutSuccessUrl(Endpoint.Authorization.BASE_URL)
                        .invalidateHttpSession(true));

        return http.build();
    }

    private void configureBase(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void configureRequests(HttpSecurity http) throws Exception {
        var configurer = http.authorizeRequests();

        RolePrivilege.list()
                .forEach(rolePrivilege -> {
                    Role role = rolePrivilege.getEndpointPermit().getRole();

                    rolePrivilege.getEndpointPermit().getEndpointRules()
                            .forEach(rule -> {
                                var conf = (rule.isAllMethods())
                                        ? configurer.mvcMatchers(rule.getUrl())
                                        : configurer.mvcMatchers(rule.getHttpMethod(), rule.getUrl());

                                if (role.equals(Role.ANONYMOUS)) {
                                    conf.permitAll();
                                } else {
                                    conf.hasAuthority(role.name());
                                }
                            });
                });

        configurer.anyRequest().authenticated();
    }
}
