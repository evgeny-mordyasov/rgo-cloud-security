package rgo.cloud.security.config.rule;

import org.springframework.http.HttpMethod;

public class EndpointRule {
    private final EndpointMethod endpointMethod;
    private final String url;

    private EndpointRule(EndpointMethod endpointMethod, String url) {
        this.endpointMethod = endpointMethod;
        this.url = url;
    }

    public static EndpointRule all(String url) {
        return method(null, url);
    }

    public static EndpointRule get(String url) {
        return method(HttpMethod.GET, url);
    }

    public static EndpointRule post(String url) {
        return method(HttpMethod.POST, url);
    }

    public static EndpointRule put(String url) {
        return method(HttpMethod.PUT, url);
    }

    public static EndpointRule patch(String url) {
        return method(HttpMethod.PATCH, url);
    }

    public static EndpointRule delete(String url) {
        return method(HttpMethod.DELETE, url);
    }

    private static EndpointRule method(HttpMethod method, String url) {
        return new EndpointRule(new EndpointMethod(method), url);
    }

    public boolean isAllMethods() {
        return endpointMethod.isAllMethods();
    }

    public HttpMethod getHttpMethod() {
        return endpointMethod.getHttpMethod();
    }

    public String getUrl() {
        return url;
    }
}

