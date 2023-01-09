package rgo.cloud.security.config.util;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import rgo.cloud.common.api.rest.BaseErrorResponse;
import rgo.cloud.common.api.rest.Status;
import rgo.cloud.common.api.rest.StatusCode;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static rgo.cloud.common.api.util.JsonUtil.toJson;
import static rgo.cloud.common.api.util.RequestUtil.JSON;

public final class ForbiddenHandler {
    private ForbiddenHandler() {
    }

    public static final AccessDeniedHandler accessDenied = (rq, res, exp)  -> modifyResponse(res);

    public static final AuthenticationEntryPoint authenticationEntryPoint = (rq, res, exp)  -> modifyResponse(res);

    private static void modifyResponse(HttpServletResponse res) throws IOException {
        res.setContentType(JSON);
        res.setStatus(200);
        res.getWriter().write(toJson(
                new BaseErrorResponse(new Status(StatusCode.FORBIDDEN))));
    }
}
