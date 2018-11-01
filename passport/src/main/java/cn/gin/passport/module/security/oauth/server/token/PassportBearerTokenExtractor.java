package cn.gin.passport.module.security.oauth.server.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import cn.gin.passport.common.util.StringUtils;

@Component
public class PassportBearerTokenExtractor extends BearerTokenExtractor {

    /**
     * The default root logger
     */
    private static final Log logger = LogFactory.getLog(BearerTokenExtractor.class);

    @Override
    protected String extractToken(HttpServletRequest request) {

        // First check the header...
        String token = extractHeaderToken(request);

        // Second check the cookie...
        if (token == null) {
            logger.debug("Token not found in headers. Trying request cookies.");
            token = extractCookieToken(request);
        }

        // bearer type allows a request parameter as well
        if (token == null) {
            logger.debug("Token not found in cookies. Trying request parameters.");
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
            if (token == null) {
                logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
            }
            else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
            }
        }

        return token;
    }

    /**
     * Extract the OAuth bearer token from cookie.
     *
     * @param request - The request
     * @return The token, or null if no OAuth authorization header was supplied
     */
    protected String extractCookieToken(HttpServletRequest request) {

        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (StringUtils.equals("TOKEN", cookie.getName())) {
                String value = cookie.getValue();
                if (!StringUtils.isBlank(value)) {
                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value);
                    return value;
                }
            }
        }

        return null;
    }
}
