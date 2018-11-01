package cn.gin.passport.module.security.oauth.client.github.service.operation;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * The component of Github service provider in Spring Social is used to provide the operation method of OAuth2
 */
public class GithubOAuth2Template extends OAuth2Template {

    public GithubOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

}