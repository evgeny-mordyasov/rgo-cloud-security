package rgo.cloud.security.config.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "module-properties.auth.cookie")
public class JwtProperties {
    private final String authCookieName;
    private final String path;
    private final int expirationHours;
    private final String secret;

    public JwtProperties(String authCookieName, String path, int expirationHours, String secret) {
        this.authCookieName = authCookieName;
        this.path = path;
        this.expirationHours = expirationHours;
        this.secret = secret;
    }

    public String getAuthCookieName() {
        return authCookieName;
    }

    public String getPath() {
        return path;
    }

    public int getExpirationHours() {
        return expirationHours;
    }

    public String getSecret() {
        return secret;
    }
}
