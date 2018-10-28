package cn.gin.passport.module.security.authentication;

import cn.gin.passport.common.Constants;
import cn.gin.passport.common.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * The default root logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        final boolean debug = logger.isDebugEnabled();

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException(
                    "Client ID/Secret not found in Basic Authentication Authorization header");
        }

        try {

            String[] tokens = extractAndDecodeHeader(header, request);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            if (debug) {
                this.logger.debug("Basic Authentication Authorization header found for " +
                        "client-id: {}, client-secret: %{}", clientId, clientSecret);
            }

            final ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException(
                        "Cannot find the matched client details, client-id:" + clientId);
            }
            else if (StringUtils.equals(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException(
                        "Invalid client secret for client-id:" + clientId);
            }

            TokenRequest tokenRequest = new TokenRequest(Maps.newHashMapWithExpectedSize(2), clientId,
                    clientDetails.getScope(), "oauth-form");
            final OAuth2Request oauth2Request = tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oauth2Authentication = new OAuth2Authentication(oauth2Request, authentication);
            final OAuth2AccessToken accessToken = tokenServices.createAccessToken(oauth2Authentication);

            response.setContentType(Constants.Http.APPLICATION_JSON);
            response.getWriter().write(objectMapper.writeValueAsString(accessToken));
        }
        catch (AuthenticationException failed) {

            String errorMessage = "Authentication information is abnormal, the token is generated failed: " + failed;

            if (debug) {
                this.logger.debug(errorMessage);
            }

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(Constants.Http.APPLICATION_JSON);
            response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
        }
    }

    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     * Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
            throws IOException {

        byte[] base64Token = header.substring(6).getBytes(Constants.System.DEFAULT_CHARSET);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, Constants.System.DEFAULT_CHARSET);

        int delim = token.indexOf(Constants.Mark.COLON);

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }
}