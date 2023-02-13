package rgo.cloud.security.config.util;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import rgo.cloud.common.api.rest.BaseErrorResponse;
import rgo.cloud.common.api.rest.Status;
import rgo.cloud.common.api.rest.StatusCode;

import static rgo.cloud.common.api.util.JsonUtil.toJson;
import static rgo.cloud.common.spring.util.RequestUtil.JSON;

public final class ForbiddenHandler {
    private ForbiddenHandler() {
    }

    public static final AccessDeniedHandler accessDenied = (rq, res, exp)  -> {
        res.setContentType(JSON);
        res.setStatus(403);
        res.getWriter().write(toJson(
                new BaseErrorResponse(new Status(StatusCode.FORBIDDEN))));
    };

    public static final AuthenticationEntryPoint authenticationEntryPoint = (rq, res, exp)  -> {
        res.setContentType(JSON);
        res.setStatus(401);
        res.getWriter().write(toJson(
                new BaseErrorResponse(new Status(StatusCode.UNAUTHORIZED))));
    };
}
