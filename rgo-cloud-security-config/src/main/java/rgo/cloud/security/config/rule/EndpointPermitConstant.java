package rgo.cloud.security.config.rule;

import rgo.cloud.common.api.model.Role;
import rgo.cloud.security.config.util.Endpoint;
import java.util.List;

import static rgo.cloud.security.config.rule.EndpointRule.*;

public final class EndpointPermitConstant {
    private static final List<EndpointRule> ADMIN_RULES = List.of(
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

    private static final List<EndpointRule> CLIENT_RULES = List.of(
    );

    private static final List<EndpointRule> ANONYMOUS_RULES = List.of(
            all(Endpoint.Authorization.BASE_URL),
            get(Endpoint.SWAGGER_UI),
            get(Endpoint.API_DOCS),
            get(Endpoint.Classification.BASE_URL),
            openGet(Endpoint.File.BASE_URL),
            openGet(Endpoint.File.BASE_URL + Endpoint.File.DOCUMENT_ID_VARIABLE),
            get(Endpoint.File.BASE_URL + Endpoint.File.FREE_LANGUAGES),
            get(Endpoint.Language.BASE_URL),
            get(Endpoint.Me.BASE_URL),
            get(Endpoint.Client.BASE_URL)
    );

    private EndpointPermitConstant() {
    }

    public static EndpointPermit admin() {
        return new EndpointPermit(ADMIN_RULES, Role.ADMIN);
    }

    public static EndpointPermit client() {
        return new EndpointPermit(CLIENT_RULES, Role.USER);
    }

    public static EndpointPermit anonymous() {
        return new EndpointPermit(ANONYMOUS_RULES, Role.ANONYMOUS);
    }
}
