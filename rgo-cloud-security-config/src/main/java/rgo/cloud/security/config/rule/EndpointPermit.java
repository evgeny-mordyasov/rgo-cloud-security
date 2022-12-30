package rgo.cloud.security.config.rule;

import rgo.cloud.common.api.model.Role;

import java.util.List;

public class EndpointPermit {
    private final List<EndpointRule> endpointRules;
    private final Role role;

    public EndpointPermit(List<EndpointRule> endpointRules, Role role) {
        this.endpointRules = endpointRules;
        this.role = role;
    }

    public List<EndpointRule> getEndpointRules() {
        return endpointRules;
    }

    public Role getRole() {
        return role;
    }
}