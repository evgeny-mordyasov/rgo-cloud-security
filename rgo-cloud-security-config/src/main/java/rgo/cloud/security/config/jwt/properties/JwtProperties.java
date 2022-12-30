package rgo.cloud.security.config.jwt.properties;

public class JwtProperties {
    private final String secret;
    private final Long expirationHours;

    public JwtProperties() {
        this.secret = "ru.gold.ordance";
        this.expirationHours =  168L;
    }

    public String getSecret() {
        return secret;
    }

    public Long getExpirationHours() {
        return expirationHours;
    }
}
