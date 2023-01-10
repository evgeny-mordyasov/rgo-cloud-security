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
        return method(null, url, false);
    }

    public static EndpointRule openAll(String url) {
        return method(null, url, true);
    }

    public static EndpointRule get(String url) {
        return method(HttpMethod.GET, url, false);
    }

    public static EndpointRule openGet(String url) {
        return method(HttpMethod.GET, url, true);
    }

    public static EndpointRule post(String url) {
        return method(HttpMethod.POST, url, false);
    }

    public static EndpointRule openPost(String url) {
        return method(HttpMethod.POST, url, true);
    }

    public static EndpointRule put(String url) {
        return method(HttpMethod.PUT, url, false);
    }

    public static EndpointRule openPut(String url) {
        return method(HttpMethod.PUT, url, true);
    }

    public static EndpointRule patch(String url) {
        return method(HttpMethod.PATCH, url, false);
    }

    public static EndpointRule openPatch(String url) {
        return method(HttpMethod.PATCH, url, true);
    }

    public static EndpointRule delete(String url) {
        return method(HttpMethod.DELETE, url, false);
    }

    public static EndpointRule openDelete(String url) {
        return method(HttpMethod.DELETE, url, true);
    }

    private static EndpointRule method(HttpMethod method, String url, boolean isOpen) {
        String param = isOpen ? url : url + "/**";
        return new EndpointRule(new EndpointMethod(method), param);
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

