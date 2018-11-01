package cn.gin.passport.module.security.oauth.client.github.service.api.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.gin.passport.module.security.oauth.client.github.service.api.GithubApi;
import cn.gin.passport.module.security.oauth.client.github.service.api.GithubUserDetails;

/**
 * Implementation of Github OAuth service provider API interface in Spring Social
 */
public class GithubApiImpl extends AbstractOAuth2ApiBinding implements GithubApi {

    /**
     * The default root logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Get Github user's details information
     */
    private static final String URL_USER_DETAILS = "https://api.github.com/user";

    /**
     * Jackson object mapper
     */
    private ObjectMapper mapper = new ObjectMapper();

    public GithubApiImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public GithubUserDetails getGithubUserDetails() {

        String resp = getRestTemplate().getForObject(URL_USER_DETAILS, String.class);
        GithubUserDetails userDetails = null;

        try {
            userDetails = mapper.readValue(resp, GithubUserDetails.class);
        }
        catch (IOException ioException) {
            logger.error("Convert the github response content to a GithubUserDetails object failed", ioException);
        }

        return userDetails;
    }
}
