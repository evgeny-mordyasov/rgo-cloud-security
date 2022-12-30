package rgo.cloud.security.config.rule;

import rgo.cloud.common.api.model.Role;
import rgo.cloud.security.config.util.Endpoint;
import java.util.List;

import static rgo.cloud.security.config.rule.EndpointRule.*;

public final class EndpointPermitConstant {
    private static final List<EndpointRule> ANONYMOUS_RULES = List.of(
            all(Endpoint.Authorization.BASE_URL + "/**"),
            get(Endpoint.Classification.BASE_URL + "/**"),
            get(Endpoint.File.BASE_URL + "/**"),
            get(Endpoint.Language.BASE_URL + "/**"),
            get(Endpoint.Me.BASE_URL + "/**"),
            get(Endpoint.Client.BASE_URL)
    );

    private static final List<EndpointRule> CLIENT_RULES = List.of(
            get(Endpoint.File.BASE_URL + Endpoint.File.RESOURCE)
    );

    private static final List<EndpointRule> ADMIN_RULES = List.of(
            all(Endpoint.Client.BASE_URL + "/**"),
            post(Endpoint.Classification.BASE_URL),
            post(Endpoint.File.BASE_URL),
            post(Endpoint.Language.BASE_URL),
            put(Endpoint.Classification.BASE_URL),
            put(Endpoint.Language.BASE_URL),
            patch(Endpoint.File.BASE_URL),
            delete(Endpoint.Classification.BASE_URL),
            delete(Endpoint.File.BASE_URL),
            delete(Endpoint.Language.BASE_URL)
    );

    private EndpointPermitConstant() {
    }

    public static EndpointPermit anonymous() {
        return new EndpointPermit(ANONYMOUS_RULES, Role.ANONYMOUS);
    }

    public static EndpointPermit client() {
        return new EndpointPermit(CLIENT_RULES, Role.USER);
    }

    public static EndpointPermit admin() {
        return new EndpointPermit(ADMIN_RULES, Role.ADMIN);
    }
}
