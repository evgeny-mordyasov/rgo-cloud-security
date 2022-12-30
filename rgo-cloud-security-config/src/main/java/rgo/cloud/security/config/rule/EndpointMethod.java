package rgo.cloud.security.config.rule;

import org.springframework.http.HttpMethod;

public class EndpointMethod {
    private final HttpMethod httpMethod;

    public EndpointMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public boolean isAllMethods() {
        return httpMethod == null;
    }
}