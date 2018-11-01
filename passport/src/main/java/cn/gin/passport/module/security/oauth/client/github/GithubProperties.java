package cn.gin.passport.module.security.oauth.client.github;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class GithubProperties extends SocialProperties {

    private String providerId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}