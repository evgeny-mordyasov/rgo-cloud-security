package rgo.cloud.security.config.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static rgo.cloud.security.config.rule.EndpointPermitConstant.*;

public enum RolePrivilege {
    ADMIN(admin()),
    CLIENT(client()),
    ANONYMOUS(anonymous());

    private final EndpointPermit endpointPermit;

    RolePrivilege(EndpointPermit endpointPermit) {
        this.endpointPermit = endpointPermit;
    }

    public static List<RolePrivilege> list() {
        return Arrays.stream(RolePrivilege.values())
                .collect(Collectors.toUnmodifiableList());
    }

    public EndpointPermit getEndpointPermit() {
        return endpointPermit;
    }
}