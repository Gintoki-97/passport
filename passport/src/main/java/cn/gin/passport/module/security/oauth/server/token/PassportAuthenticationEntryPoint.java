package cn.gin.passport.module.security.oauth.server.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import cn.gin.passport.common.util.JsonObject;

@Component
public class PassportAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        Throwable cause = authException.getCause();
        JsonObject json = new JsonObject();
        json.setSuccess(false);

        if (cause != null && cause instanceof InvalidTokenException) {
            json.setCode(1);
            json.setMsg("Invalid token: " + cause.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(json.toJson());
        }
        else {
            super.commence(request, response, authException);
        }
    }
}