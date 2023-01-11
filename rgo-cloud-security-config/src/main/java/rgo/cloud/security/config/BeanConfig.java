package rgo.cloud.security.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rgo.cloud.security.config.jwt.JwtProvider;
import rgo.cloud.security.config.jwt.properties.JwtProperties;
import rgo.cloud.security.config.service.ClientDetailsService;
import rgo.cloud.security.config.service.ClientDetailsServiceStub;

@Configuration
@ConfigurationPropertiesScan
public class BeanConfig {

    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    @Bean
    public JwtProvider jwtProvider(UserDetailsService service, JwtProperties properties) {
        return new JwtProvider(service, properties);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:5173")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    @Profile("!test")
    public UserDetailsService userDetailsService() {
        return new ClientDetailsService();
    }

    @Bean
    @Profile("test")
    public UserDetailsService userDetailsServiceStub() {
        return new ClientDetailsServiceStub();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
}
